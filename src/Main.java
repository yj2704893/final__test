import java.sql.*;
import java.util.Scanner;

public class Main {
    private static String DriveName = "org.sqlite.JDBC";
    public static void main(String[] args) {
        System.out.println("coming out");
        try {
            Class.forName(DriveName);
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jiangyuting/Downloads/final__test/LibraryInformation.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from BookInformation");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 1 for Staff or 0 for Student:");
        int userType = scanner.nextInt();

        if (userType == 1) {
            LibraryGUI gui = new LibraryGUI();
        } else if (userType == 0) {
            StudentLoginGUI guii = new StudentLoginGUI();
        } else {
            System.out.println("Invalid input!");
        }
    }


}
