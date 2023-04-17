import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentProfileGUI extends JFrame implements ActionListener {
    private JButton searchButton;

    public StudentProfileGUI(Student student) {
        // Set up the JFrame
        super("Student Profile");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the components
        JLabel nameLabel = new JLabel("Name:");
        JLabel idLabel = new JLabel("Student ID:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel collegeLabel = new JLabel("College:");
        JLabel nameValueLabel = new JLabel(student.getName());
        JLabel idValueLabel = new JLabel(student.getStudentID());
        JLabel emailValueLabel = new JLabel(student.getEmail());
        JLabel collegeValueLabel = new JLabel(student.getCollege());
        searchButton = new JButton("Search Book");

        // Create the layout
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(nameLabel);
        panel.add(nameValueLabel);
        panel.add(idLabel);
        panel.add(idValueLabel);
        panel.add(emailLabel);
        panel.add(emailValueLabel);
        panel.add(collegeLabel);
        panel.add(collegeValueLabel);
        panel.add(new JPanel());
        panel.add(searchButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        searchButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // If the 'Search' button is clicked, close this window and open the 'SearchBookGUI' window
        dispose();
        new SearchBookGUI();
    }

}



