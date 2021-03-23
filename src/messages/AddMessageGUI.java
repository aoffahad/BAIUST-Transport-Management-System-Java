package messages;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddMessageGUI {
    public void setupMessage(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        
        JLabel title = new JLabel("Title: ");
        title.setBounds(100, 100, 50, 50);
        panel.add(title);
        JLabel body = new JLabel("Body: ");
        body.setBounds(100, 200, 50, 50);
        panel.add(body);
        JLabel sender = new JLabel("Sender: ");
        sender.setBounds(100, 300, 50, 50);
        panel.add(sender);
        
        JTextField enterTitle = new JTextField();
        enterTitle.setBounds(300, 100, 500, 50);
        panel.add(enterTitle);
        JTextField enterBody = new JTextField();
        enterBody.setBounds(300, 200, 500, 50);
        panel.add(enterBody);
        JTextField enterSender = new JTextField();
        enterSender.setBounds(300, 300, 500, 50);
        panel.add(enterSender);
        

        JButton submitButton = new JButton("Add Message");
        submitButton.setBounds(400, 800, 300, 50);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                    String stitle = enterTitle.getText();
                    String sbody = enterBody.getText();
                    String ssender = (String) enterSender.getText();
                    String stype = "Checker";
                  
                    MessageDBAdapter messageDBAdapter = new MessageDBAdapter();
                try {
                    //  studentDBAdapter.setupDB();
                    if(messageDBAdapter.hasMessage(stitle)){
                        JOptionPane.showMessageDialog(frame, "Message title ( " + stitle + " ) already exists in database");
                        return;    
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AddMessageGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    messageDBAdapter.addMessage(stitle, sbody, ssender, stype);
                } catch (Exception ex) {
                    Logger.getLogger(AddMessageGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                    JOptionPane.showMessageDialog(frame, "Message added successfully");
                    

                

            }
        });
        panel.add(submitButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }

}
