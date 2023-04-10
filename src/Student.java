import java.util.ArrayList;

public class Student {
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    String name;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    String email;

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    String studentID;

    public String getCollege() {
        return college;
    }

    String college;

    public void setCollege(String college) {
        this.college = college;
    }

    ArrayList<Book> booksBorrowed;

    public Student(String name, String email, String studentID, String college) {
        this.name = name;
        this.email = email;
        this.studentID = studentID;
        this.college = college;
        this.booksBorrowed = new ArrayList<>();
    }

    public void viewProfile() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Student ID: " + studentID);
        System.out.println("College: " + college);
        System.out.println("Books borrowed:");
        for (Book book : booksBorrowed) {
            System.out.println(book.name + " " + book.author + " " + book.number + " " + book.category + " " + book.status);
        }
    }


}








