package admin;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import student.*;
import messages.*;
import schedule.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAMNOON
 */
public class AddNewAdminGUI {
    public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        
        JLabel title = new JLabel("Name: ");
        title.setBounds(100, 100, 50, 50);
        panel.add(title);
        JLabel body = new JLabel("Password: ");
        body.setBounds(100, 200, 50, 50);
        panel.add(body);
        
        
        JTextField enterTitle = new JTextField();
        enterTitle.setBounds(300, 100, 500, 50);
        panel.add(enterTitle);
        JTextField enterBody = new JTextField();
        enterBody.setBounds(300, 200, 500, 50);
        panel.add(enterBody);
       
        

        JButton submitButton = new JButton("Add New Admin");
        submitButton.setBounds(400, 800, 300, 50);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                    String stitle = enterTitle.getText();
                    String sbody = enterBody.getText();
                      
                     boolean isAdmin = false;
                     try {
                         isAdmin = hasAdmin(stitle, sbody);
                     } catch (Exception ex) {
                         Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     if(isAdmin){
                         // go to admin gui
                         JOptionPane.showMessageDialog(frame, "Admin already exists.");
                         return;
                     }
                     else{
                        
                        Connection con = null;
                        try {
                            con = getConnection();
                        } catch (Exception ex) {
                            Logger.getLogger(AddNewAdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        PreparedStatement statement = null;
                        try {
                            statement = con.prepareStatement("insert into admin(name, pass) values(?, ?)");
                        } catch (SQLException ex) {
                            Logger.getLogger(AddNewAdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            statement.setString(1, stitle);
                            statement.setString(2, sbody);
                            statement.executeUpdate();
                            con.close();
                            JOptionPane.showMessageDialog(frame, "Admin Added.");
                        } catch (SQLException ex) {
                            Logger.getLogger(AddNewAdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                         
                        
                     }

            }
        });
        panel.add(submitButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }public static Connection getConnection() throws  Exception{
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
    
}
