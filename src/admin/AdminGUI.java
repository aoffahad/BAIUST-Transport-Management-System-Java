package admin;

import messages.ViewMessageList;
import student.MaintainStudent;
import schedule.MaintainSchedule;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminGUI {
    public void setupAdmin(){
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
        
        JButton schedule = new JButton("Change Settings");
        schedule.setBounds(100, 600, 300, 100);
        panel.add(schedule);

        JButton maintainSchedule = new JButton("Maintain Schedule");
        maintainSchedule.setBounds(700, 300, 300, 100);
        panel.add(maintainSchedule);

        JButton maintainStudents = new JButton("Maintain Students");
        maintainStudents.setBounds(700, 600, 300, 100);
        panel.add(maintainStudents);

        messages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMessageList message = new ViewMessageList();
                try {
                    message.setupTable();
                } catch (Exception ex) {
                    Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        schedule.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent e) {
                AdminSettings adminSettings = new AdminSettings();
                adminSettings.setupAdmin();
            }
        });

        maintainSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaintainSchedule mschedule = new MaintainSchedule();
                mschedule.setupAdmin();
            }
        });

        maintainStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaintainStudent mstudent = new MaintainStudent();
                mstudent.setupAdmin();
            }
        });




        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
