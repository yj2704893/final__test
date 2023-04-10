import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryGUI extends JFrame implements ActionListener {

    private JTextField nameField, emailField, idField, collegeField, bookNameField, bookNumberField, bookAuthorField, bookCategoryField, bookStatusField;
    private JTextArea outputArea;

    private JButton addStudentButton, addBookButton, updateStudentButton, updateBookButton,
            deleteStudentButton, deleteBookButton, listStudentButton;

    private Staff staff;

    public LibraryGUI() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        staff = new Staff();

        JPanel studentPanel = new JPanel(new GridLayout(0, 2));
        studentPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        studentPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        studentPanel.add(nameField);
        studentPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        studentPanel.add(emailField);
        studentPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        studentPanel.add(idField);
        studentPanel.add(new JLabel("College:"));
        collegeField = new JTextField();
        studentPanel.add(collegeField);
        addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(this);
        studentPanel.add(addStudentButton);
        updateStudentButton = new JButton("Update Student");
        updateStudentButton.addActionListener(this);
        studentPanel.add(updateStudentButton);
        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.addActionListener(this);
        studentPanel.add(deleteStudentButton);
        listStudentButton = new JButton("List All Student");
        listStudentButton.addActionListener(this);
        studentPanel.add(listStudentButton);

        JPanel bookPanel = new JPanel(new GridLayout(0, 2));
        bookPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));
        bookPanel.add(new JLabel("Name:"));
        bookNameField = new JTextField();
        bookPanel.add(bookNameField);
        bookPanel.add(new JLabel("Number:"));
        bookNumberField = new JTextField();
        bookPanel.add(bookNumberField);
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

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        outputArea = new JTextArea(10, 50);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(studentPanel, BorderLayout.NORTH);
        mainPanel.add(bookPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addStudentButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            String id = idField.getText();
            String college = collegeField.getText();
            Student newStudent = new Student(name, email, id, college);
            staff.addStudent(newStudent);
            outputArea.setText("Student added successfully!");
        } else if (source == updateStudentButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            String id = idField.getText();
            String college = collegeField.getText();
            Student studentToUpdate = staff.findStudentByID(id);
            if (studentToUpdate != null) {
                staff.updateStudent(studentToUpdate, name, email, id, college);
                outputArea.setText("Student updated successfully!");
            } else {
                outputArea.setText("Student not found.");
            }
        } else if (source == deleteStudentButton) {
            String id = idField.getText();
            Student studentToDelete = staff.findStudentByID(id);
            if (studentToDelete != null) {
                staff.deleteStudent(studentToDelete);
                outputArea.setText("Student deleted successfully!");
            } else {
                outputArea.setText("Student not found.");
            }
        }
        if (source == addBookButton) {
            String bookName = bookNameField.getText();
            String bookNumber = bookNumberField.getText();
            String bookAuthor = bookAuthorField.getText();
            String bookCategory = bookCategoryField.getText();
            String bookStatus = bookStatusField.getText();
            Book newBook = new Book(bookName, bookNumber, bookAuthor, bookCategory, bookStatus);
            staff.addBook(newBook);
            outputArea.setText("Book added successfully!");
        } else if (source == updateBookButton) {
            String bookName = bookNameField.getText();
            String bookNumber = bookNumberField.getText();
            String bookAuthor = bookAuthorField.getText();
            String bookCategory = bookCategoryField.getText();
            String bookStatus = bookStatusField.getText();
            Book bookToUpdate = staff.findBookByNumber(bookNumber);
            if (bookToUpdate != null) {
                staff.updateBook(bookToUpdate, bookName, bookNumber, bookAuthor, bookCategory, bookStatus);
                outputArea.setText("Book updated successfully!");
            } else {
                outputArea.setText("Book not found.");
            }
        } else if (source == deleteBookButton) {
            String bookNumber = bookNumberField.getText();
            Book bookToDelete = staff.findBookByNumber(bookNumber);
            if (bookToDelete != null) {
                staff.deleteBook(bookToDelete);
                outputArea.setText("Book deleted successfully!");
            } else {
                outputArea.setText("Book not found.");
            }


        }

    }
}

