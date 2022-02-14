package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Learning Progress Tracker.");
        while (true) {
            input = scanner.nextLine().toLowerCase().strip();
            if (input.isEmpty() || input.isBlank()) {
                System.out.println("No input.");
            } else if ("exit".equals(input)) {
                System.out.println("Bye!");
                break;
            } else if (input.contains("add")) {
                addStudents(scanner);
            } else if ("back".equals(input)) {
                System.out.println("Enter 'exit' to exit the program.");
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    private static void addStudents(Scanner scanner) {
        System.out.println("Enter student credentials or 'back' to return:");
        int countStudents = 0;
        String input = "";
        while (true) {
            input = scanner.nextLine().trim();
            if (input.contains("back")) {
                break;
            }

            boolean valid = checkCorrectCredentials(input);
            if (valid) {
                System.out.println("The student has been added.");
                countStudents++;
            }
        }

        System.out.printf("Total %d students have been added.%n", countStudents);
    }

    private static String getCredentials(Scanner scanner) {
        return scanner.nextLine().trim();
    }

    private static boolean checkCorrectCredentials(String creds) {
        String[] parts = creds.split("\\s+");

        if (parts.length < 3) {
            System.out.println("Incorrect credentials.");
            return false;
        }

        String firstName = parts[0];
        if (!isFirstNameValid(firstName)) {
            System.out.println("Incorrect first name.");
            return false;
        }

        String email = parts[parts.length - 1];
        if (!isEmailValid(email)) {
            System.out.println("Incorrect email.");
            return false;
        }

        String lastName = creds.split(email)[0].strip().split("\\s+", 2)[1];
        if (!isLastNameValid(lastName)) {
            System.out.println("Incorrect last name.");
            return false;
        }

        return true;
    }

    private static boolean isEmailValid(String input) {
        String pattern = "^[A-Za-z0-9.]+@[A-Za-z0-9.]+\\.\\w+$";
        return input.matches(pattern);
    }

    private static boolean isFirstNameValid(String input) {
        String pattern = "^(([A-Za-z]{2,})|([A-Za-z]+[\\-'][A-Za-z]+))+$";
        return input.matches(pattern);
    }

    private static boolean isLastNameValid(String input) {
        String pattern = "^(([A-Za-z]{2,})|([A-Za-z]+[\\-'\\s][A-Za-z]+)[\\s-]*)+$";
        return input.matches(pattern);
    }
}
