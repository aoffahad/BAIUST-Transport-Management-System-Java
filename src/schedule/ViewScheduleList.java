package schedule;


import com.formdev.flatlaf.FlatLightLaf;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
public class ViewScheduleList {
    public void setupTable() throws Exception{
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        ScheduleDBAdapter scheduleAdapter = new ScheduleDBAdapter();
        scheduleAdapter.setupDB();
        ArrayList <ScheduleModelClass> scheduleData = scheduleAdapter.getAllSchedule();
        String[] col = {"TIME", "FROM", "TO", "BUSNO"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        
        JTable scheduleTable = new JTable();
        scheduleTable.setModel(model);
        scheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scheduleTable.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(scheduleTable);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        if(scheduleData.isEmpty()){
            JOptionPane.showMessageDialog(frame, "NO Schedule Found");
            
        }
        for(int i=0; i<scheduleData.size(); i++){
            model.addRow(new Object[] {scheduleData.get(i).getTime(), scheduleData.get(i).getFrom(),
                scheduleData.get(i).getTo(), scheduleData.get(i).getBusno()
            });
         }
        
        frame.add(scroll);
        frame.setVisible(true);
  
        panel.setLayout(null);
    }
}
