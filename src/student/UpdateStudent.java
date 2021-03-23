package student;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateStudent {
    public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);
        String[] slevel = {"01", "02", "03", "04"};
        String[] sterm = {"I", "II"};
        String[] sType = {"Residential", "Non-Residential"};
        JLabel id = new JLabel("ID: ");
        id.setBounds(100, 100, 50, 50);
        panel.add(id);
        JLabel level = new JLabel("Level: ");
        level.setBounds(100, 200, 50, 50);
        panel.add(level);
        JLabel term = new JLabel("Term: ");
        term.setBounds(100, 300, 50, 50);
        panel.add(term);
        JLabel type = new JLabel("Type: ");
        type.setBounds(100, 400, 50, 50);
        panel.add(type);
       
        JTextField enterID = new JTextField();
        enterID.setBounds(300, 100, 500, 50);
        panel.add(enterID);
        JComboBox enterLevel = new JComboBox(slevel);
        enterLevel.setBounds(300, 200, 500, 50);
        panel.add(enterLevel);
        JComboBox enterTerm = new JComboBox(sterm);
        enterTerm.setBounds(300, 300, 500, 50);
        panel.add(enterTerm);
        JComboBox enterType = new JComboBox(sType);
        enterType.setBounds(300, 400, 500, 50);
        panel.add(enterType);
      

        JButton submitButton = new JButton("Update Student");
        submitButton.setBounds(400, 600, 300, 50);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sid = enterID.getText();
                String slevel = (String) enterLevel.getSelectedItem();
                String sterm = (String) enterTerm.getSelectedItem();
                String stype = (String) enterType.getSelectedItem();
                try{
                    int newId = Integer.parseInt(sid);
                    StudentDBAdapter studentDBAdapter = new StudentDBAdapter();
                   // studentDBAdapter.setupDB();
                    if(studentDBAdapter.hasStudent(newId) != true){
                         JOptionPane.showMessageDialog(frame, "Student ID " + sid + " does not exist in database");
                         enterID.setText("");
                         return;    
                    }
                    studentDBAdapter.updateStudent(newId, slevel, sterm, stype);
                    JOptionPane.showMessageDialog(frame, "Student ID " + sid + " updated successfully.");
                }
                catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(frame, "Wrong ID");
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(submitButton);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }

}
