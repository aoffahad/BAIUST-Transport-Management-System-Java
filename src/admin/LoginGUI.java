package admin;


import admin.AdminGUI;
import admin.CheckerGUI;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import student.StudentDBAdapter;
import student.StudentGUI;

public class LoginGUI implements ItemListener {
    public JComboBox comboBox;
    public String selectedPerson;
    public void setup(){
        FlatLightLaf.install();
        Font font = new Font(Font.DIALOG, Font.PLAIN, 18);
        UIManager.put("Button.font", font);
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        

        String[] options = {"Admin", "Checker", "Student"};
        comboBox = new JComboBox(options);
        comboBox.setBounds(400, 100, 100, 50);
        comboBox.setSelectedIndex(0);
        panel.add(comboBox);

        JLabel boxLabel = new JLabel("Select type: ");
        boxLabel.setBounds(300, 100, 100, 50);
        panel.add(boxLabel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(300, 200, 100, 50);
        panel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(400, 200, 300, 50);
        //userText.setText("Enter your username here");
        panel.add(userText);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(300, 300, 100, 50);
        panel.add(passLabel);
        JTextField passText = new JTextField(20);
        passText.setBounds(400, 300, 300, 50);
       // passText.setText("Enter your password here");
        panel.add(passText);

        JButton loginButton = new JButton("Enter");
        loginButton.setBounds(400, 400, 75, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(comboBox.getSelectedItem().equals("Admin")) {
                     
                     
                     
                     
                     
                     boolean isAdmin = false;
                     try {
                         isAdmin = hasAdmin(userText.getText(), passText.getText());
                     } catch (Exception ex) {
                         Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     if(isAdmin){
                         // go to admin gui
                         JOptionPane.showMessageDialog(frame, "Login successful");
                         AdminGUI adminGUI = new AdminGUI();
                         adminGUI.setupAdmin();
                     }
                     else{
                         JOptionPane.showMessageDialog(frame, "Invalid login");
                         userText.setText(null);
                         passText.setText(null);
                     }
                 }
                 else if(comboBox.getSelectedItem().equals("Checker")){
                     if(userText.getText().equals("fahad") && passText.getText().equals("1106017")){
                         JOptionPane.showMessageDialog(frame, "Login successful");
                         CheckerGUI checkerGUI = new CheckerGUI();
                         checkerGUI.setupAdmin();
                     }
                     else{
                         JOptionPane.showMessageDialog(frame, "Invalid login");
                         userText.setText(null);
                         passText.setText(null);
                     }
                 }
                 else{
                    int id = Integer.parseInt(userText.getText());
                    String pass = passText.getText();
                    if(pass.isEmpty()){
                        pass = null;
                    }
                    StudentDBAdapter studentDBAdapter = new StudentDBAdapter();
                     try {
                         //  studentDBAdapter.setupDB();
                         if(studentDBAdapter.verifyStudent(id, pass)){
                             JOptionPane.showMessageDialog(frame, "Login successful");
                             StudentGUI student = new StudentGUI();
                             student.setupAdmin(id, pass);
                             return;
                         }
                         else{
                         JOptionPane.showMessageDialog(frame, "Invalid login");
                         userText.setText(null);
                         passText.setText(null);
                         
                         }
                     } catch (Exception ex) {
                         Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    
                    
                    
                }
            }
        });

        panel.add(loginButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);

    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
    java.util.Enumeration keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get (key);
      if (value instanceof javax.swing.plaf.FontUIResource)
        UIManager.put (key, f);
      }
    } 

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == comboBox){
            selectedPerson = (String) comboBox.getSelectedItem();
        }
    }
    public boolean hasAdmin(String name, String password) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("select pass from admin where name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            if(resultSet.getString(1).equals(password))
              return true;
        }
        return false;
    }
    public static Connection getConnection() throws  Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String jdbcURL = "jdbc:mysql://localhost:3306/sadd";
            String username = "root";
            String password = "admin";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected");
            return conn;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
