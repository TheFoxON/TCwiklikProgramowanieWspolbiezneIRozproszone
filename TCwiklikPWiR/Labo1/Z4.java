package Labo1;

import java.util.Scanner;

public class Z4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Write date in day.month.year:");
            String date = scanner.nextLine();
            String[] parts = date.split("\\.");


            if (parts.length != 3) {
                System.out.println("Invalid date format.");
                return;
            }


            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);


            if (checkDate(day, month, year)) {
                int dayInYear = countYearDay(day, month, year);
                System.out.println(date + " is " + dayInYear + " of the year " + year + ".");
            } else {
                System.out.println("An invalid date was entered.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
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

    public static int countYearDay(int day, int month, int year) {
        int dayInMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            dayInMonth[2] = 29;
        }

        int dayInYear = day;
        for (int i = 1; i < month; i++) {
            dayInYear += dayInMonth[i];
        }

        return dayInYear;
    }
}

