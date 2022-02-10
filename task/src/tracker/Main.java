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
            } else {
                System.out.println("Error: unknown command!");
            }
        }
    }
}
