package tracker;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentDaoImpl studentDatabase = new StudentDaoImpl();

    public void run() {
        String input = "";
        System.out.println("Learning Progress Tracker.");
        while (true) {
            input = scanner.nextLine().toLowerCase().strip();
            if (input.isEmpty() || input.isBlank()) {
                System.out.println("No input.");
            }

            switch (input) {
                case "exit":
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                case "add students":
                    addStudentsMenu(scanner);
                    break;
                case "add points":
                    addPointsMenu(scanner);
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program.");
                    break;
                case "find":
                    findStudentMenu(scanner);
                    break;
                case "list":
                    System.out.println("Students:");
                    studentDatabase.getAllStudents()
                            .keySet()
                            .forEach(System.out::println);
                    break;
                default:
                    System.out.println("Unknown command!");
                    break;
            }
        }
    }

    private void findStudentMenu(Scanner scanner) {
        System.out.println("Enter an id or 'back' to return:");
        String input = "";
        while (true) {
            input = scanner.nextLine().trim();
            if (input.contains("back")) {
                break;
            }
            findStudent(input);
        }
    }

    private void findStudent(String input) {
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return;
        }
        Student student = studentDatabase.getStudent(id);
        if (student == null) {
            System.out.printf("No student is found for id=%d.%n", id);
            return;
        }
        System.out.println(student);
    }

    private void addPointsMenu(Scanner scanner) {
        System.out.println("Enter an id and points or 'back' to return:");
        String input = "";
        while (true) {
            input = scanner.nextLine().trim();
            if (input.contains("back")) {
                break;
            }

            boolean isAdded = addPoints(input);
            if (isAdded) {
                System.out.println("Points updated.");
            }

        }
    }

    private boolean addPoints(String input) {
        boolean isInputValid = Credentials.validatePoints(input);
        if (isInputValid) {
            String[] parts = input.split("\\s+");
            int studentId = Integer.parseInt(parts[0]);
            int javaPoints = Integer.parseInt(parts[1]);
            int dsaPoints = Integer.parseInt(parts[2]);
            int dbPoints = Integer.parseInt(parts[3]);
            int springPoints = Integer.parseInt(parts[4]);
            Student student = studentDatabase.getStudent(studentId);
            if (student == null) {
                System.out.printf("No student is found for id=%d.%n", studentId);
                return false;
            }
            Points tempPoints = new Points(javaPoints, dsaPoints, dbPoints, springPoints);
            student.setPoints(tempPoints);
            studentDatabase.updateStudent(student);
            return true;
        }
        System.out.println("Incorrect points format.");
        return false;
    }

    private void addStudentsMenu(Scanner scanner) {
        System.out.println("Enter student credentials or 'back' to return:");
        int countStudents = 0;
        String input = "";
        while (true) {
            input = scanner.nextLine().trim();
            if (input.contains("back")) {
                break;
            }

            boolean valid = Credentials.validate(input);
            if (valid) {
                boolean isAdded = addStudent(input);
                if (isAdded) {
                    System.out.println("The student has been added.");
                    countStudents++;
                }
            }
        }

        System.out.printf("Total %d students have been added.%n", countStudents);
    }

    private boolean addStudent(String credentials) {
        String[] parts = credentials.split("\\s+");
        String firstName = parts[0];
        String email = parts[parts.length - 1];
        String lastName = credentials.split(email)[0].strip().split("\\s+", 2)[1];
        boolean emailExists = studentDatabase.getAllStudents()
                .values()
                .stream()
                .anyMatch(student -> student.getEmail().equals(email));
        if (emailExists) {
            System.out.println("This email is already taken.");
            return false;
        }
        int studentId = studentDatabase.getNextStudentId();
        Student student = new Student(studentId, firstName, lastName, email);
        studentDatabase.addStudent(student);
        return true;
    }
}
