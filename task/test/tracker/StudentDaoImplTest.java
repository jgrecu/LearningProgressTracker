package tracker;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoImplTest {

    StudentDaoImpl db = new StudentDaoImpl();

    @Test
    void getAllStudents() {
        Student student = new Student(1000, "Samuel", "Johnson", "sam.johnson@gmail.com");
        Map<Integer, Student> testDB = Map.of(1000, student);
        db.addStudent(student);
        assertEquals(testDB, db.getAllStudents());
    }

    @Test
    void getStudent() {
        Student student = new Student(1000, "Samuel", "Johnson", "sam.johnson@gmail.com");
        db.addStudent(student);
        assertEquals(student, db.getStudent(1000));
    }

    @Test
    void addStudent() {
        Student student = new Student(1000, "Samuel", "Johnson", "sam.johnson@gmail.com");
        db.addStudent(student);
        boolean result = student.equals(db.getStudent(1000));
        assertTrue(result);
    }

    @Test
    void updateStudent() {
        Student student = new Student(1000, "Samuel", "Johnson", "sam.johnson@gmail.com");
        Student updatedStudent = new Student(1000, "Sam", "Johnson", "sam.johnson@gmail.com");
        db.addStudent(student);
        db.updateStudent(updatedStudent);
        boolean result = updatedStudent.equals(db.getStudent(1000));
        assertTrue(result);
    }

    @Test
    void getNextStudentId() {
        int result = 10000;
        assertEquals(result, db.getNextStudentId());
    }
}