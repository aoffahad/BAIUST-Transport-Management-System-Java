package schedule;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddSchedule {
     public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
         String[] sBox = {"Campus-01", "Campus-02", "Male Hostel(22No Gate)",
        "Female Hostel(17c)", "Kotbari", "Cumilla city"};
        String[] sBus = {"Bus No-01", "Bus No-02", "Bus No-03", "Bus No-04",
        "Bus No-05", "Bus No-06"};
        JLabel time = new JLabel("Time: ");
        time.setBounds(100, 100, 50, 50);
        panel.add(time);
        JLabel from = new JLabel("From: ");
        from.setBounds(100, 200, 50, 50);
        panel.add(from);
        JLabel to = new JLabel("To: ");
        to.setBounds(100, 300, 50, 50);
        panel.add(to);
        JLabel busno = new JLabel("Bus No: ");
        busno.setBounds(100, 400, 50, 50);
        panel.add(busno);
        /*
        JTextField enterTime = new JTextField();
        
        enterTime.setBounds(300, 100, 500, 50);
        panel.add(enterTime);
        */
        JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Time(new Date().getTime())); // will only show the current time]
        timeSpinner.setBounds(300, 100, 500, 50);
        panel.add(timeSpinner);
        
        
        JComboBox enterFrom = new JComboBox(sBox);
        enterFrom.setBounds(300, 200, 500, 50);
        panel.add(enterFrom);
        JComboBox enterTo = new JComboBox(sBox);
        enterTo.setBounds(300, 300, 500, 50);
        panel.add(enterTo);
        JComboBox enterBusNo = new JComboBox(sBus);
        enterBusNo.setBounds(300, 400, 500, 50);
        panel.add(enterBusNo);


        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(400, 600, 300, 50);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    Date date = (Date) timeSpinner.getModel().getValue();
                    Time stime = new Time(date.getTime());
                    String sfrom = (String) enterFrom.getSelectedItem();
                    String sto = (String) enterTo.getSelectedItem();
                    String sbusno = (String) enterBusNo.getSelectedItem();
                    ScheduleDBAdapter schedule = new ScheduleDBAdapter();
                
                    schedule.setupDB();
                    if(schedule.hasSchedule(stime))
                    {
                        JOptionPane.showMessageDialog(frame, "Schedule " + stime + " already exists in database.");
                        return;
                    }
                    schedule.addSchedule(stime, sfrom, sto, sbusno);
                    JOptionPane.showMessageDialog(frame, "Schedule " + stime + " added successfully.");
                } catch (Exception ex) {
                    Logger.getLogger(AddSchedule.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                    
               
            }
        });
        panel.add(submitButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
