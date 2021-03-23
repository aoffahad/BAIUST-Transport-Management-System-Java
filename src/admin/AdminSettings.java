package admin;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
public class AdminSettings {
     public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);

        JButton messages =  new JButton("Add admins");
        messages.setBounds(100, 300, 800, 100);
        panel.add(messages);

        JButton schedule = new JButton("Modify admins");
        schedule.setBounds(100, 600, 800, 100);
        panel.add(schedule);

        

        messages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewAdminGUI myadmin = new AddNewAdminGUI();
                myadmin.setupAdmin();
            }
        });

        schedule.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent e) {
                ModifyAdminGUI myadmin = new ModifyAdminGUI();
                myadmin.setupAdmin();
               
            }
        });

      




        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
