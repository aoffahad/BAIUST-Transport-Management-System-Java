package schedule;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAMNOON
 */
public class RemoveSchedule {
    public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
         
        JLabel time = new JLabel("Time: ");
        time.setBounds(100, 100, 50, 50);
        panel.add(time);
        
        JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Time(new Date().getTime())); // will only show the current time] 
        timeSpinner.setBounds(300, 100, 500, 50);
        panel.add(timeSpinner);
        

        


        JButton submitButton = new JButton("Confirm");
        submitButton.setBounds(400, 600, 300, 50);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     Time stime = (Time) timeSpinner.getModel().getValue();
                    
                    ScheduleDBAdapter schedule = new ScheduleDBAdapter();
                try {
                    schedule.setupDB();
                    if(schedule.hasSchedule(stime) != true)
                    {
                         JOptionPane.showMessageDialog(frame, "Schedule " + stime + " does not exist in database.");
                        return;
                    }
                    schedule.removeSchedule(stime);
                    JOptionPane.showMessageDialog(frame, "Schedule " + stime + " removed successfully.");
                    
                } catch (Exception ex) {
                    Logger.getLogger(RemoveSchedule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(submitButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
