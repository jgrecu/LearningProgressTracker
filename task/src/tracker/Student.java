package tracker;

import java.util.*;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Points points;

    public Student(int id, String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.points = new Points();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points.addJava(points.getJava());
        this.points.addDsa(points.getDsa());
        this.points.addDatabases(points.getDatabases());
        this.points.addSpring(points.getSpring());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && firstName.equals(student.firstName) && lastName.equals(student.lastName) && email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return id + " points: " + points;
    }
}
