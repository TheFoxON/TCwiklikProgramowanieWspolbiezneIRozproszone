public class ClockApp {
    public static void main(String[] args) {
        Clock clock = new Clock();
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            while (true) {
                System.out.println("1. Set the clock");

                System.out.println("2. Write down time");

                System.out.println("3. Change format (12/24)");

                System.out.println("4. Tick");

                System.out.println("5. Exit");

                System.out.print("Select an option: ");
                int options = scanner.nextInt();

                switch (options) {
                    case 1:
                        System.out.print("Enter the hour: ");
                        int hour = scanner.nextInt();

                        System.out.print("Enter the minute: ");
                        int minute = scanner.nextInt();

                        System.out.print("Enter the seconds: ");
                        int second = scanner.nextInt();
                        clock.Set(hour, minute, second);
                        break;
                    case 2:
                        clock.Write();
                        break;
                    case 3:
                        System.out.print("Change format (1 - 24h, 2 - 12h): ");
                        int format = scanner.nextInt();
                        clock.format(format == 1);
                        break;
                    case 4:
                        clock.tick();
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Unsupported option.");
                        break;
                }
            }
        }
    }
}