package tracker;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentDaoImpl studentDatabase = new StudentDaoImpl();
    private Statistics statistics = new Statistics();
    Map<String, String> courses = Map.of(
            "java", "Java",
            "dsa", "DSA",
            "databases", "Databases",
            "spring", "Spring"
    );

    public void run() {

        System.out.println("Learning Progress Tracker.");
        while (true) {
            String command = getInput();
            switch (command) {
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
                    if (studentDatabase.getAllStudents().isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("Students:");
                        studentDatabase.getAllStudents()
                                .keySet()
                                .stream()
                                .sorted()
                                .forEach(System.out::println);
                    }
                    break;
                case "statistics":
                    getStats();
                    break;
                case "notify":
                    sentNotification();
                    break;
                default:
                    break;
            }
        }
    }

    private void sentNotification() {
        int counter = 0;
        for (Map.Entry<Integer, Student> entry : studentDatabase.getAllStudents().entrySet()) {
            if (entry.getValue().notification()) {
                counter++;
            }
        }
        System.out.printf("Total %s students have been notified.", counter);
    }

    private void getStats() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        statistics.printStats();
        while (true) {
            String course = scanner.nextLine().trim().toLowerCase();
            if ("back".equals(course)) {
                break;
            }
            if (!courses.containsKey(course)) {
                System.out.println("Unknown course.");
                continue;
            }
            System.out.println(courses.get(course));
            System.out.println("id    points    completed");

            Map<Integer, Double> completions = studentDatabase.getAllStudents().entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPoints().getCompletion(course)));
            Map<Integer, Double> sorted = completions.entrySet().stream()
                    .sorted(Map.Entry.<Integer, Double>comparingByValue(Comparator.reverseOrder())
                            .thenComparing(Map.Entry.comparingByKey()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            for (Map.Entry<Integer, Double> entry : sorted.entrySet()) {
                studentDatabase.getAllStudents().get(entry.getKey()).courseStats(course);
            }
        }
    }

    public String getInput() {
        while (true) {
            String input = scanner.nextLine().toLowerCase().strip();

            if (input.isEmpty() || input.isBlank()) {
                System.out.println("No input.");
            } else if (Arrays.stream(Commands.values())
                    .anyMatch(command -> Objects.equals(command.value, input))) {
                return input;
            } else {
                System.out.println("Unknown command!");
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
            int studentId;

            try {
                studentId = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.out.printf("No student is found for id=%s.%n", parts[0]);
                return false;
            }

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
            statistics.addTasks(studentId, javaPoints, dsaPoints, dbPoints, springPoints);
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
