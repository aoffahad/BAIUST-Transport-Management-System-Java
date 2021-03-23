package schedule;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaintainSchedule {
    public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);

        JButton updateSchedule =  new JButton("Update Schedule");
        updateSchedule.setBounds(100, 300, 300, 100);
        panel.add(updateSchedule);

        JButton removeSchedule = new JButton("Delete Schedule");
        removeSchedule.setBounds(100, 600, 300, 100);
        panel.add(removeSchedule);

        JButton viewSchedule = new JButton("View Schedule");
        viewSchedule.setBounds(700, 300, 300, 100);
        panel.add(viewSchedule);

        JButton addSchedule = new JButton("Add Schedule");
        addSchedule.setBounds(700, 600, 300, 100);
        panel.add(addSchedule);

        updateSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateSchedule updateSchedule = new UpdateSchedule();
                updateSchedule.setupAdmin();
            }
        });

        removeSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   RemoveSchedule remove = new RemoveSchedule();
                   remove.setupAdmin();
            }
        });

        viewSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewScheduleList viewSchedule = new ViewScheduleList();
                try {
                    viewSchedule.setupTable();
                } catch (Exception ex) {
                    Logger.getLogger(MaintainSchedule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        addSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSchedule addSchedule = new AddSchedule();
                addSchedule.setupAdmin();
            }
        });




        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
