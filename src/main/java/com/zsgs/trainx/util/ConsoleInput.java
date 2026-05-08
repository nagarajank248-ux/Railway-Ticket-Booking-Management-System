package com.zsgs.trainx.util;

import java.util.Scanner;

public class ConsoleInput {
    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleInput() {}

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a valid number.");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String readPassword(String prompt) {
        System.out.print(prompt);
        // In a real console app, System.console().readPassword() could be used for masking
        return scanner.nextLine().trim();
    }

    public static int readMenuChoice(String prompt, int min, int max) {
        while (true) {
            int choice = readInt(prompt);
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.printf("  [!] Please enter a number between %d and %d.%n", min, max);
        }
    }

    public static void pressEnterToContinue() {
        System.out.print("\n  Press Enter to continue...");
        scanner.nextLine();
    }

    public static void close() {
        scanner.close();
    }
}
