import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentProfileGUI extends JFrame implements ActionListener {
    private JButton searchButton;
    public StudentProfileGUI(Student student) {
        // Set up the JFrame
        super("Student Profile");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the components
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel idLabel = new JLabel("Student ID:");
        JLabel collegeLabel = new JLabel("College:");
        JLabel nameValueLabel = new JLabel(student.getName());
        JLabel emailValueLabel = new JLabel(student.getEmail());
        JLabel idValueLabel = new JLabel(student.getStudentID());
        JLabel collegeValueLabel = new JLabel(student.getCollege());
        searchButton = new JButton("Search Book");

        // Create the layout
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameValueLabel);
        panel.add(emailLabel);
        panel.add(emailValueLabel);
        panel.add(idLabel);
        panel.add(idValueLabel);
        panel.add(collegeLabel);
        panel.add(collegeValueLabel);
        panel.add(searchButton);

        // Add the components to the JFrame
        add(panel);

        // Set the action listener for the 'Search' button
        searchButton.addActionListener(this);

        // Show the JFrame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // If the 'Search' button is clicked, close this window and open the 'SearchBookGUI' window
        dispose();
        new SearchBookGUI();
    }

}



