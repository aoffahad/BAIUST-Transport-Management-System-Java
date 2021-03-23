package admin;

import messages.AddMessageGUI;
import schedule.ViewScheduleList;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckerGUI {
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
        messages.setBounds(100, 300, 800, 100);
        panel.add(messages);

        JButton schedule = new JButton("View Schedule");
        schedule.setBounds(100, 600, 800, 100);
        panel.add(schedule);

        

        messages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMessageGUI newMessage = new AddMessageGUI();
                newMessage.setupMessage();
            }
        });

        schedule.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent e) {
                ViewScheduleList viewSchedule = new ViewScheduleList();
                    try {
                        viewSchedule.setupTable();
                    } catch (Exception ex) {
                        Logger.getLogger(CheckerGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
            }
        });

      




        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
