package tracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student = new Student(10000, "John", "Smith", "jsmith@gmail.com");

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
        assertEquals(0, student.getPoints().getJava());
        assertEquals(0, student.getPoints().getDsa());
        assertEquals(0, student.getPoints().getDatabases());
        assertEquals(0, student.getPoints().getSpring());
    }

    @Test
    void setPoints() {
        Points points = new Points(10, 10, 10, 10);
        student.setPoints(points);
        assertEquals(10, student.getPoints().getJava());
        assertEquals(10, student.getPoints().getDsa());
        assertEquals(10, student.getPoints().getDatabases());
        assertEquals(10, student.getPoints().getSpring());
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