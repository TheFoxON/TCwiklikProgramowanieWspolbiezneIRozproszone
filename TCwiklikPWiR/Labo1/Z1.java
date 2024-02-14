package Labo1;

import java.util.Scanner;
public class Z1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Calculate the number of days in a month in a non-leap year.");
            System.out.println("2. Calculate the number of days in a month and year.");

            int wybor = scanner.nextInt();

            if (wybor == 1) {
                System.out.print("Enter the month number (1-12): ");
                int month = scanner.nextInt();

                int days = numberOfDaysInMonth(month, 0);
                System.out.println("Number of days in a month" + month + " in a non-leap year: " + days);
            } else if (wybor == 2) {
                System.out.print("Enter the month number: (1-12): ");
                int month = scanner.nextInt();
                System.out.print("Enter the year: ");
                int year = scanner.nextInt();

                int days = numberOfDaysInMonth(month, year);
                System.out.println("Number of days in a month " + month + " in year " + year + ": " + days);
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    public static int numberOfDaysInMonth(int month, int year) {
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month < 1 || month > 12) {
            return -1; 
        }

        if (month == 2 && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
            return 29;
        } else {
            return daysInMonth[month];
        }
    }
}
