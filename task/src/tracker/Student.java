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

    public void courseStats(String course) {
        switch (course) {
            case "java":
                if (!points.javaStats().equals("0.0")) {
                    System.out.printf("%d %s        %s%%\n", id, points.getJava(), points.javaStats());
                }
                break;
            case "dsa":
                if (!points.dsaStats().equals("0.0")) {
                    System.out.printf("%d %s        %s%%\n", id, points.getDsa(), points.dsaStats());
                }
                break;
            case "databases":
                if (!points.dbStats().equals("0.0")) {
                    System.out.printf("%d %s        %s%%\n", id, points.getDatabases(), points.dbStats());
                }
                break;
            case "spring":
                if (!points.springStats().equals("0.0")) {
                    System.out.printf("%d %s        %s%%\n", id, points.getSpring(), points.springStats());
                }
                break;
        }
    }

    public boolean notification() {
        boolean isNotified = false;
        for (Map.Entry<String, String> entry : points.notifications.entrySet()) {
            if (entry.getValue().equals("ready")) {
                System.out.printf("To: %s\n" +
                                "Re: Your Learning Progress\n" +
                                "Hello, %s %s! You have accomplished our %s course!\n",
                        this.email, this.firstName, this.lastName, entry.getKey());
                points.notifications.put(entry.getKey(), "sent");
                isNotified = true;
            }
        }
        return isNotified;
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
