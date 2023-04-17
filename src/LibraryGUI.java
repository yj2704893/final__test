import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LibraryGUI extends JFrame implements ActionListener {

    private JButton addBookButton, updateBookButton, deleteBookButton, searchBookButton;
    private JButton addStudentButton, updateStudentButton, deleteStudentButton;
    private JTextField bookNameField, bookNumField, bookCategoryField, bookStatusField, bookAuthorField;
    private JTextField studentNameField, studentEmailField, studentIDField, studentCollegeField;
    private static String DriverName = "org.sqlite.JDBC";

    public LibraryGUI() {
        super("Staff Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel studentPanel = new JPanel(new GridLayout(0, 2));
        studentPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        studentPanel.add(new JLabel("Name:"));
        studentNameField = new JTextField();
        studentPanel.add(studentNameField);
        studentPanel.add(new JLabel("Email:"));
        studentEmailField = new JTextField();
        studentPanel.add(studentEmailField);
        studentPanel.add(new JLabel("ID:"));
        studentIDField = new JTextField();
        studentPanel.add(studentIDField);
        studentPanel.add(new JLabel("College:"));
        studentCollegeField = new JTextField();
        studentPanel.add(studentCollegeField);
        addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(this);
        studentPanel.add(addStudentButton);
        updateStudentButton = new JButton("Update Student");
        updateStudentButton.addActionListener(this);
        studentPanel.add(updateStudentButton);
        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.addActionListener(this);
        studentPanel.add(deleteStudentButton);


        JPanel bookPanel = new JPanel(new GridLayout(0, 2));
        bookPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));
        bookPanel.add(new JLabel("Book name:"));
        bookNameField = new JTextField();
        bookPanel.add(bookNameField);
        bookPanel.add(new JLabel("Number:"));
        bookNumField = new JTextField();
        bookPanel.add(bookNumField);
        bookPanel.add(new JLabel("Author:"));
        bookAuthorField = new JTextField();
        bookPanel.add(bookAuthorField);
        bookPanel.add(new JLabel("Category:"));
        bookCategoryField = new JTextField();
        bookPanel.add(bookCategoryField);
        bookPanel.add(new JLabel("Status:"));
        bookStatusField = new JTextField();
        bookPanel.add(bookStatusField);
        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(this);
        bookPanel.add(addBookButton);
        updateBookButton = new JButton("Update Book");
        updateBookButton.addActionListener(this);
        bookPanel.add(updateBookButton);
        deleteBookButton = new JButton("Delete Book");
        deleteBookButton.addActionListener(this);
        bookPanel.add(deleteBookButton);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(studentPanel, BorderLayout.NORTH);
        mainPanel.add(bookPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setSize(700, 450);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Get the book information entered by the user
        String bookName = bookNameField.getText();
        String bookNum = bookNumField.getText();
        String bookCategory = bookCategoryField.getText();
        String bookStatus = bookStatusField.getText();
        String bookAuthor = bookAuthorField.getText();

        // Get the student information entered by the user
        String studentName = studentNameField.getText();
        String studentEmail = studentEmailField.getText();
        String studentID = studentIDField.getText();
        String studentCollege = studentCollegeField.getText();

        // Add a book to the database if the user clicked the Add Book button
        if (e.getSource() == addBookButton) {
            try {
                Class.forName(DriverName);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
                Statement statement = connection.createStatement();
                int rowsInserted = statement.executeUpdate("insert into BookInformation(name, number, category, status, author) values('" + bookName + "', '" + bookNum + "', '" + bookCategory + "', '" + bookStatus + "','" + bookAuthor + "')");
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Book added successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Book could not be added.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Update a book to the database if the user clicked the Add Book button
        if (e.getSource() == updateBookButton) {
            try {
                Class.forName(DriverName);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE BookInformation SET name=?, number=?, author=? WHERE category=? AND status=?");
                preparedStatement.setString(1, bookName);
                preparedStatement.setString(2, bookNum);
                preparedStatement.setString(3, bookAuthor);
                preparedStatement.setString(4, bookCategory);
                preparedStatement.setString(5, bookStatus);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Book updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Book could not be updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Delete a book to the database if the user clicked the Add Book button
        if (e.getSource() == deleteBookButton) {
            try {
                Class.forName(DriverName);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM BookInformation WHERE number=? AND name=?");
                preparedStatement.setString(1, bookNum);
                preparedStatement.setString(2, bookName);
                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Book deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Book could not be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Add a student to the database if the user clicked the Add student button
        if (e.getSource() == addStudentButton) {
            try {
                Class.forName(DriverName);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
                Statement statement = connection.createStatement();
                int rowsInserted = statement.executeUpdate("insert into StudentInofrmation(name, id, email, college) values('" + studentName + "', '" + studentID + "', '" + studentEmail + "', '" + studentCollege + "')");
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Student added successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Student could not be added.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Update a student to the database if the user clicked the Add student button
        if (e.getSource() == updateStudentButton) {
            try {
                Class.forName(DriverName);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE StudentInofrmation SET name=?, id=? WHERE email=? AND college=?");
                preparedStatement.setString(1, studentName);
                preparedStatement.setString(2, studentID);
                preparedStatement.setString(3, studentEmail);
                preparedStatement.setString(4, studentCollege);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Student updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Student could not be updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Delete a student to the database if the user clicked the Add student button
        if (e.getSource() == deleteStudentButton) {
            try {
                Class.forName(DriverName);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM StudentInofrmation WHERE id=? AND name=?");
                preparedStatement.setString(1, studentID);
                preparedStatement.setString(2, studentName);
                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Student deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Student could not be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}
