import javax.swing.*;
import java.util.ArrayList;

public class Staff {
    ArrayList<Student> students;
    ArrayList<Book> books;

    public Staff() {
        this.students = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(Student student, String name, String id, String email, String college) {
        student.setName(name);
        student.setStudentID(id);
        student.setEmail(email);
        student.setCollege(college);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(Book book, String name, String number, String author, String category, String status) {
        book.name = name;
        book.number = number;
        book.author = author;
        book.category = category;
        book.status = status;
    }

    public void deleteBook(Book book) {
        books.remove(book);
    }

}

