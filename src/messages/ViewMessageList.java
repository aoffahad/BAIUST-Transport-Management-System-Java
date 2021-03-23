package messages;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAMNOON
 */
public class ViewMessageList {
    public void setupTable() throws Exception{
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        MessageDBAdapter messageAdapter = new MessageDBAdapter();
        messageAdapter.setupDB();
        ArrayList <MessageModelClass> messageData = messageAdapter.getAllMessage();
        String[] col = {"TITLE", "BODY", "SENDER", "TYPE"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        
        JTable messageTable = new JTable();
        messageTable.setModel(model);
        messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        messageTable.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(messageTable);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        if(messageData.isEmpty()){
            JOptionPane.showMessageDialog(frame, "NO Message Found");
            
        }
        for(int i=0; i<messageData.size(); i++){
            model.addRow(new Object[] {messageData.get(i).getTitle(), messageData.get(i).getBody(),
            messageData.get(i).getSender(), messageData.get(i).getType()});
         }
        
        frame.add(scroll);
        frame.setVisible(true);
  
        panel.setLayout(null);
    }
}
