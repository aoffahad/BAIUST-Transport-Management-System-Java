/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author SAMNOON
 */
public class StudentInfo {
    public void setupAdmin(int sid) throws Exception{
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        StudentDBAdapter studentDBAdapter = new StudentDBAdapter();
        StudentModelClass student = studentDBAdapter.getStudent(sid);
        JLabel id = new JLabel("ID: ");
        id.setBounds(100, 25, 50, 50);
        panel.add(id);
        JLabel name = new JLabel("Name: ");
        name.setBounds(100, 100, 50, 50);
        panel.add(name);
        JLabel dept = new JLabel("Dept: ");
        dept.setBounds(100, 300, 50, 50);
        panel.add(dept);
        JLabel level = new JLabel("Level: ");
        level.setBounds(100, 400, 50, 50);
        panel.add(level);
        JLabel term = new JLabel("Term: ");
        term.setBounds(100, 500, 50, 50);
        panel.add(term);
        JLabel type = new JLabel("Type: ");
        type.setBounds(100, 600, 50, 50);
        panel.add(type);
        JLabel busFee = new JLabel("Bus Fee: ");
        busFee.setBounds(100, 700, 50, 50);
        panel.add(busFee);
        JLabel pass = new JLabel("Password: ");
        pass.setBounds(100, 200, 50, 50);
        panel.add(pass);
        
        JLabel enterID = new JLabel(String.valueOf(student.getId()));
        enterID.setBounds(300, 25, 500, 50);
        panel.add(enterID);
        JLabel enterName = new JLabel(student.getName());
        enterName.setBounds(300, 100, 500, 50);
        panel.add(enterName);
        JLabel enterDept = new JLabel(student.getDept());
        enterDept.setBounds(300, 300, 500, 50);
        panel.add(enterDept);
        JLabel enterLevel = new JLabel(student.getLevel());
        enterLevel.setBounds(300, 400, 500, 50);
        panel.add(enterLevel);
        JLabel enterTerm = new JLabel(student.getTerm());
        enterTerm.setBounds(300, 500, 500, 50);
        panel.add(enterTerm);
        JLabel enterType = new JLabel(student.getType());
        enterType.setBounds(300, 600, 500, 50);
        panel.add(enterType);
        JLabel enterBusFee = new JLabel(String.valueOf(student.getBusFee()));
        enterBusFee.setBounds(300, 700, 500, 50);
        panel.add(enterBusFee);
        JLabel enterPass = new JLabel(student.getPass());
        enterPass.setBounds(300, 200, 500, 50);
        panel.add(enterPass);
       

        
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
