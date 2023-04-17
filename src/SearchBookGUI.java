import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchBookGUI extends JFrame implements ActionListener {
    private JLabel bookNumLabel;
    private JTextField bookNumField;
    private JButton searchButton, borrowButton, returnButton;
    private JLabel bookNameLabel, bookNumResultLabel, bookCategoryLabel, bookStatusLabel, bookAuthorLabel;
    private static String DriverName = "org.sqlite.JDBC";

    public SearchBookGUI() {
        super("Search Books");

        bookNumLabel = new JLabel("Search by book number:");
        bookNumField = new JTextField(20);
        searchButton = new JButton("Search Book");
        borrowButton = new JButton("Borrow");
        returnButton = new JButton("Return");
        bookNameLabel = new JLabel("Book name:");
        bookNumResultLabel = new JLabel("Book number:");
        bookCategoryLabel = new JLabel("Book category:");
        bookStatusLabel = new JLabel("Book status:");
        bookAuthorLabel = new JLabel("Book author:");

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(bookNumLabel);
        searchPanel.add(bookNumField);
        searchPanel.add(searchButton);

        JPanel bookInfoPanel = new JPanel(new GridLayout(5, 1));
        bookInfoPanel.add(bookNameLabel);
        bookInfoPanel.add(bookNumResultLabel);
        bookInfoPanel.add(bookCategoryLabel);
        bookInfoPanel.add(bookStatusLabel);
        bookInfoPanel.add(bookAuthorLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(searchButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(bookInfoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        searchButton.addActionListener(this);
        borrowButton.addActionListener(this);
        returnButton.addActionListener(this);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Get the book number entered by the user
        String bookNum = bookNumField.getText();

        // Search for the book in the database and display its information
        try {
            Class.forName(DriverName);
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from BookInformation where number='" + bookNum + "'");
            if (resultSet.next()) {
                bookNameLabel.setText("Book name: " + resultSet.getString("name"));
                bookNumResultLabel.setText("Book number: " + resultSet.getString("number"));
                bookCategoryLabel.setText("Book category: " + resultSet.getString("category"));
                bookStatusLabel.setText("Book status: " + resultSet.getString("status"));
                bookAuthorLabel.setText("Book author: " + resultSet.getString("author"));
            } else {
                JOptionPane.showMessageDialog(null, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            resultSet.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // Borrow the book if the user clicked the Borrow button
        if (e.getSource() == borrowButton) {
            try {
                Class.forName(DriverName);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
                Statement statement = connection.createStatement();
                int rowsUpdated = statement.executeUpdate("update BookInformation set status='borrowed' where number='" + bookNum + "'");
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Book borrowed successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Book could not be borrowed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Return the book if the user clicked the Borrow button
        if (e.getSource() == returnButton) {
            try {
                Class.forName(DriverName);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
                Statement statement = connection.createStatement();
                int rowsUpdated = statement.executeUpdate("update BookInformation set status='available' where number='" + bookNum + "'");
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Book returned successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Book could not be returned.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}


