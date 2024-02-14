package Labo1;

import java.util.Scanner;
public class Z3 
{
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the date in the day, month, year format:");
            int day = scanner.nextInt();
            int month = scanner.nextInt();
            int year = scanner.nextInt();

            if (checkDate(day, month, year)) {
                String monthName = monthName(month);
                System.out.println("Date: " + day + " " + monthName + " " + year);
            } else {
                System.out.println("An invalid date was entered.");
            }
        }
    }

    public static boolean checkDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }

        int daysInMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }

        return day >= 1 && day <= daysInMonth[month];
    }

    public static boolean isLeapYear(int rok) {
        return (rok % 400 == 0) || ((rok % 4 == 0) && (rok % 100 != 0));
    }

    public static String monthName(int miesiac) {
        String[] monthNames = {"", "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
        if (miesiac >= 1 && miesiac <= 12) {
            return monthNames[miesiac];
        } else {
            return "Error";
        }
    }
}

