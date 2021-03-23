package student;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentGUI {
    public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        String[] sdept = {"CSE", "EEE", "CIVIL", "BBA", "ENGLISH", "LAW"};
        String[] slevel = {"01", "02", "03", "04"};
        String[] sterm = {"I", "II"};
        String[] stype = {"Residential", "Non-Residential"};
        JLabel id = new JLabel("ID: ");
        id.setBounds(100, 25, 50, 50);
        panel.add(id);
        JLabel name = new JLabel("Name: ");
        name.setBounds(100, 100, 50, 50);
        panel.add(name);
        JLabel dept = new JLabel("Dept: ");
        dept.setBounds(100, 300, 50, 50);
        panel.add(dept);
        JLabel level = new JLabel("Level: ");
        level.setBounds(100, 400, 50, 50);
        panel.add(level);
        JLabel term = new JLabel("Term: ");
        term.setBounds(100, 500, 50, 50);
        panel.add(term);
        JLabel type = new JLabel("Type: ");
        type.setBounds(100, 600, 50, 50);
        panel.add(type);
        JLabel busFee = new JLabel("Bus Fee: ");
        busFee.setBounds(100, 700, 50, 50);
        panel.add(busFee);
        JLabel pass = new JLabel("Password: ");
        pass.setBounds(100, 200, 50, 50);
        panel.add(pass);
        
        JTextField enterID = new JTextField();
        enterID.setBounds(300, 25, 500, 50);
        panel.add(enterID);
        JTextField enterName = new JTextField();
        enterName.setBounds(300, 100, 500, 50);
        panel.add(enterName);
        JComboBox enterDept = new JComboBox(sdept);
        enterDept.setBounds(300, 300, 500, 50);
        panel.add(enterDept);
        JComboBox enterLevel = new JComboBox(slevel);
        enterLevel.setBounds(300, 400, 500, 50);
        panel.add(enterLevel);
        JComboBox enterTerm = new JComboBox(sterm);
        enterTerm.setBounds(300, 500, 500, 50);
        panel.add(enterTerm);
        JComboBox enterType = new JComboBox(stype);
        enterType.setBounds(300, 600, 500, 50);
        panel.add(enterType);
        JTextField enterBusFee = new JTextField();
        enterBusFee.setBounds(300, 700, 500, 50);
        panel.add(enterBusFee);
        JTextField enterPass = new JTextField();
        enterPass.setBounds(300, 200, 500, 50);
        panel.add(enterPass);
       

        JButton submitButton = new JButton("Add Student");
        submitButton.setBounds(400, 800, 300, 50);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sid = enterID.getText();
                    String sname = enterName.getText();
                    String sdept = (String) enterDept.getSelectedItem();
                    String slevel = (String) enterLevel.getSelectedItem();
                    String sterm = (String) enterTerm.getSelectedItem();
                    String stype = (String) enterType.getSelectedItem();
                    String sfee = enterBusFee.getText();
                    String spass = enterPass.getText();
                    int newId = Integer.parseInt(sid);
                    int fee = Integer.parseInt(sfee);
                    StudentDBAdapter studentDBAdapter = new StudentDBAdapter();
                  //  studentDBAdapter.setupDB();
                    if(studentDBAdapter.hasStudent(newId)){
                         JOptionPane.showMessageDialog(frame, "Student ID " + sid + " already exists in database");
                         return;    
                    }
                    if(spass.isEmpty()){
                         JOptionPane.showMessageDialog(frame, "Enter a valid password.");
                         return;
                    }
                    studentDBAdapter.addStudent(newId, sname, sdept, slevel, sterm, stype, fee, spass);
                    JOptionPane.showMessageDialog(frame, "Student ID " + sid + " added successfully");
                    

                }
                catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(frame,"Wrong ID");
                    enterID.setText("");
                    enterBusFee.setText("");
                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });
        panel.add(submitButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }

}
