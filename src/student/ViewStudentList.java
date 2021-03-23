package student;


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
public class ViewStudentList {
    public void setupTable() throws Exception{
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        StudentDBAdapter studentAdapter = new StudentDBAdapter();
        studentAdapter.setupDB();
        ArrayList <StudentModelClass> studentData = studentAdapter.getAllStudents();
        String[] col = {"ID", "NAME", "DEPT", "LEVEL", "TERM", "TYPE", "BUSFEE"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        
        JTable studentTable = new JTable();
        studentTable.setModel(model);
        studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        studentTable.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(studentTable);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        if(studentData.isEmpty()){
            JOptionPane.showMessageDialog(frame, "NO Student Found");
            
        }
        for(int i=0; i<studentData.size(); i++){
            model.addRow(new Object[] {studentData.get(i).getId(), studentData.get(i).getName(),
            studentData.get(i).getDept(), studentData.get(i).getLevel(),
            studentData.get(i).getTerm(), studentData.get(i).getType(), studentData.get(i).getBusFee()});
        }
        
        frame.add(scroll);
        frame.setVisible(true);
  
        panel.setLayout(null);
    }
}
