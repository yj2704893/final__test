import java.util.ArrayList;

public class Student {

    String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    String email;
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    String studentID;
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public String getStudentID() {
        return studentID;
    }

    String college;
    public String getCollege() {
        return college;
    }
    public void setCollege(String college) {
        this.college = college;
    }

    ArrayList<Book> booksBorrowed;

    public Student(String name, String studentID, String email, String college) {
        this.name = name;
        this.studentID = studentID;
        this.email = email;
        this.college = college;
        this.booksBorrowed = new ArrayList<>();
    }

}








