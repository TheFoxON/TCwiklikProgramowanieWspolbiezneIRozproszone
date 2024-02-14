package Labo1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Z5 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data1 = null;
            Date data2 = null;

            try {
                System.out.print("Set first date in DD.MM.YYYY: ");
                String input1 = scanner.nextLine();
                data1 = sdf.parse(input1);

                System.out.print("Set second date in DD.MM.YYYY: ");
                String input2 = scanner.nextLine();
                data2 = sdf.parse(input2);

            } catch (Exception e) {
                System.out.println("Invalid date format. The correct format is DD.MM.YYYY.");
                return;
            }

            long dayDifference = calculateDayDifference(data1, data2);
            System.out.println("Day difference between dates: " + dayDifference + " days.");
        }
    }

    public static long calculateDayDifference(Date data1, Date data2) {
        long timeDifference = data2.getTime() - data1.getTime();
        return Math.abs(timeDifference / (1000 * 60 * 60 * 24));
    }
}

