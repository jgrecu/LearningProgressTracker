package tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointsTest {

    private Points points;

    @BeforeEach
    void createPoints() {
        int javaPoints = 10;
        int dsaPoints = 10;
        int databasesPoints = 10;
        int springPoints = 10;
        points = new Points(javaPoints, dsaPoints, databasesPoints, springPoints);
    }

    @Test
    void getJava() {
        assertEquals(10, points.getJava());
    }

    @Test
    void addJava() {
        points.addJava(5);
        assertEquals(15, points.getJava());
    }

    @Test
    void getDsa() {
        assertEquals(10, points.getDsa());
    }

    @Test
    void addDsa() {
        points.addDsa(4);
        assertEquals(14, points.getDsa());
    }

    @Test
    void getDatabases() {
        assertEquals(10, points.getDatabases());
    }

    @Test
    void addDatabases() {
        points.addDatabases(6);
        assertEquals(16, points.getDatabases());
    }

    @Test
    void getSpring() {
        assertEquals(10, points.getSpring());
    }

    @Test
    void addSpring() {
        points.addSpring(9);
        assertEquals(19, points.getSpring());
    }

    @Test
    void testToString() {
        assertEquals("Java=10; DSA=10; Databases=10; Spring=10", points.toString());
    }
}