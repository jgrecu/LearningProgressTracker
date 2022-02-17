package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;

    @BeforeEach
    void createStudent() {
        int id = 10000;
        String firstName = "John";
        String lastName = "Smith";
        String email = "jsmith@gmail.com";
        student = new Student(id, firstName, lastName, email);
    }

    @Test
    void getId() {
        assertEquals(10000, student.getId());
    }

    @Test
    void getFirstName() {
        assertEquals("John", student.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Smith", student.getLastName());
    }

    @Test
    void getEmail() {
        assertEquals("jsmith@gmail.com", student.getEmail());
    }

    @Test
    void getPoints() {
        Points testPoints = new Points();
        boolean result = testPoints.equals(student.getPoints());
        assertTrue(result);
    }

    @Test
    void setPoints() {
        Points testPoints = new Points(10, 10, 10, 10);
        student.setPoints(testPoints);
        boolean result = testPoints.equals(student.getPoints());
        assertTrue(result);
    }

    @Test
    void equals() {
        Student student1 = new Student(10000, "John", "Smith", "jsmith@gmail.com");
        boolean result = student.equals(student1);
        assertTrue(result);
    }

    @Test
    void testToString() {
        assertEquals("10000 points: Java=0; DSA=0; Databases=0; Spring=0", student.toString());
    }
}