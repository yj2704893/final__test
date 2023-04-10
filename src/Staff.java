import javax.swing.*;
import java.util.ArrayList;

public class Staff {
    ArrayList<Student> students;
    ArrayList<Book> books;

    JTextArea outputArea;


    public Staff() {
        this.students = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(Student student, String name, String email, String id, String college) {
        student.setName(name);
        student.setEmail(email);
        student.setStudentID(id); // update the studentID field
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

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book.name + " " + book.number + " " + book.author + " " + book.category + " " + book.status);
        }
    }

    public Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        return null; // student not found
    }

    public Book findBookByNumber(String number) {
        for (Book book : books) {
            if (book.number.equals(number)) {
                return book;
            }
        }
        return null; // student not found
    }


}

