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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import messages.AddMessageGUI;
import schedule.ViewScheduleList;

/**
 *
 * @author SAMNOON
 */
public class StudentGUI {
     public void setupAdmin(int id, String pass){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);

        JButton messages =  new JButton("Messages");
        messages.setBounds(100, 300, 300, 100);
        panel.add(messages);

        JButton schedule = new JButton("View Schedule");
        schedule.setBounds(100, 600, 300, 100);
        panel.add(schedule);
        
        JButton viewInfo = new JButton("View Personal Info");
        viewInfo.setBounds(700, 300, 300, 100);
        panel.add(viewInfo);

        JButton update = new JButton("Update Password");
        update.setBounds(700, 600, 300, 100);
        panel.add(update);
        
        

        

        messages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentMessage newMessage = new AddStudentMessage();
                newMessage.setupMessage(id);
            }
        });

        schedule.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent e) {
               ViewScheduleList viewSchedule = new ViewScheduleList();
                    
                    try {
                        viewSchedule.setupTable();
                    } catch (Exception ex) {
                        Logger.getLogger(StudentGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
            }
        });
        
        viewInfo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                   StudentInfo studentInfo = new StudentInfo();
                try {
                    studentInfo.setupAdmin(id);
                } catch (Exception ex) {
                    Logger.getLogger(StudentGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                  UpdateStudentPassword studentPass = new UpdateStudentPassword();
                  studentPass.setupAdmin(id);
            }
            
            
        });

      




        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
