import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchBookGUI extends JFrame implements ActionListener {
    private JLabel bookNumLabel;
    private JTextField bookNumField;
    private JButton searchButton;
    private JLabel bookNameLabel, bookNumResultLabel, bookCategoryLabel, bookStatusLabel;

    private static String DriverName = "org.sqlite.JDBC";

    public SearchBookGUI() {
        super("Search Books");

        bookNumLabel = new JLabel("Search by book number:");
        bookNumField = new JTextField(20);
        searchButton = new JButton("Search");
        bookNameLabel = new JLabel("Book name:");
        bookNumResultLabel = new JLabel("Book number:");
        bookCategoryLabel = new JLabel("Book category:");
        bookStatusLabel = new JLabel("Book status:");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(bookNumLabel);
        panel.add(bookNumField);
        panel.add(searchButton);
        panel.add(bookNameLabel);
        panel.add(bookNumResultLabel);
        panel.add(bookCategoryLabel);
        panel.add(bookStatusLabel);

        add(panel);

        searchButton.addActionListener(this);

        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Get the book number entered by the user
        String bookNum = bookNumField.getText();

        // Search for the book in the database and display its information
        System.out.println("Searching for book " + bookNum + "...");
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
            } else {
                bookNameLabel.setText("Book name: ");
                bookNumResultLabel.setText("Book number: ");
                bookCategoryLabel.setText("Book category: ");
                bookStatusLabel.setText("Book status: ");
                JOptionPane.showMessageDialog(null, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            resultSet.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // Close the current window and open the SearchBookGUI window
        dispose();
        new SearchBookGUI();
    }
}

