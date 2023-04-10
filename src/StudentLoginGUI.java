import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class StudentLoginGUI extends JFrame implements ActionListener {
    private JLabel idLabel, passwordLabel;
    private JTextField idField, passwordField;
    private JButton loginButton;
    private static String DriveName = "org.sqlite.JDBC";
    public StudentLoginGUI() {
        super("Student Login");

        idLabel = new JLabel("Student ID:");
        idField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(idLabel);
        panel.add(idField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        add(panel);

        loginButton.addActionListener(this);

        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        // Get the student ID and password entered by the user
        String studentId = idField.getText();
        String password = passwordField.getText();

        // Check if the student ID is found in the StudentInformation table of the database
        String name = null;
        String email = null;
        String college = null;
        boolean found = false;
        try {
            Class.forName(DriveName);
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM StudentInofrmation WHERE id = '" + studentId + "'");
            if (resultSet.next()) {
                found = true;
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                college = resultSet.getString("college");
            }
            resultSet.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (found) {
            // Create a new Student object with the ID, password, email, and college found in the database
            Student student = new Student(name, studentId, email, college);
            // Switch to the student profile window and close the current window
            StudentProfileGUI profileGUI = new StudentProfileGUI(student);
            dispose(); // Close the current window
            profileGUI.setVisible(true); // Show the student profile window
        } else {
            JOptionPane.showMessageDialog(null, "Invalid student ID or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}









