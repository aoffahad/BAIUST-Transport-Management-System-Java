package student;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveStudent {
    public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);

        JLabel id = new JLabel("ID: ");
        id.setBounds(100, 100, 50, 50);
        panel.add(id);

        JTextField enterID = new JTextField();
        enterID.setBounds(300, 100, 500, 50);
        panel.add(enterID);



        JButton submitButton = new JButton("Remove");
        submitButton.setBounds(400, 400, 300, 50);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sid = enterID.getText();
                try{
                    int newId = Integer.parseInt(sid);
                    StudentDBAdapter studentDBAdapter = new StudentDBAdapter();
                   // studentDBAdapter.setupDB();
                    if(studentDBAdapter.hasStudent(newId) != true){
                         JOptionPane.showMessageDialog(frame, "Student ID " + sid + " does not exist in database");
                         enterID.setText("");
                         return;    
                    }
                    studentDBAdapter.removeStudent(newId);
                     JOptionPane.showMessageDialog(frame, "Student ID " + sid + " removed successfully");
                        
                }
                catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(frame, "Wrong ID");
                    enterID.setText("");
                } catch (Exception ex) {
                    Logger.getLogger(RemoveStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(submitButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }

}
