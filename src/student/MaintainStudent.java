package student;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaintainStudent {
    public void setupAdmin(){
        FlatLightLaf.install();
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1200, 1000);

        JButton updateStudent =  new JButton("Update Student");
        updateStudent.setBounds(100, 300, 300, 100);
        panel.add(updateStudent);

        JButton removeStudent = new JButton("Remove Student");
        removeStudent.setBounds(100, 600, 300, 100);
        panel.add(removeStudent);

        JButton studentList = new JButton("Student List");
        studentList.setBounds(700, 300, 300, 100);
        panel.add(studentList);

        JButton addStudent = new JButton("Add Student");
        addStudent.setBounds(700, 600, 300, 100);
        panel.add(addStudent);

        updateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateStudent updateStudent = new UpdateStudent();
                updateStudent.setupAdmin();
            }
        });

        removeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveStudent removeStudent = new RemoveStudent();
                removeStudent.setupAdmin();
            }
        });

        studentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStudentList viewStudent = new ViewStudentList();
                try {
                    viewStudent.setupTable();
                } catch (Exception ex) {
                    Logger.getLogger(MaintainStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentGUI addStudentGUI = new AddStudentGUI();
                addStudentGUI.setupAdmin();
            }
        });




        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
    }
}
