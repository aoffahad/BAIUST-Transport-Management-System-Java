/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author SAMNOON
 */
public class UpdateStudentPassword {
    public void setupAdmin(int id){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        
        JLabel curPass = new JLabel("Current Password: ");
        curPass.setBounds(100, 100, 75, 50);
        panel.add(curPass);
        JLabel newPass = new JLabel("New Password: ");
        newPass.setBounds(100, 200, 75, 50);
        panel.add(newPass);
       
        
        JTextField enterCurPass = new JTextField();
        enterCurPass.setBounds(300, 100, 500, 50);
        panel.add(enterCurPass);
        JTextField enterNewPass = new JTextField();
        enterNewPass.setBounds(300, 200, 500, 50);
        panel.add(enterNewPass);
        
        JButton submitButton = new JButton("Confirm");
        submitButton.setBounds(400, 400, 300, 50);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    String currPass = enterCurPass.getText();
                    String newPass = enterNewPass.getText();
                    StudentDBAdapter studentDBAdapter = new StudentDBAdapter();
                try {
                    StudentModelClass student = studentDBAdapter.getStudent(id);
                    if(student.getPass().equals(currPass)){
                        studentDBAdapter.updatePass(id, newPass);
                        JOptionPane.showMessageDialog(frame, "Updated Successfully");
                         return; 
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "Entered existing Password does not match");
                        return; 
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStudentPassword.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        panel.add(submitButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }

    

}
