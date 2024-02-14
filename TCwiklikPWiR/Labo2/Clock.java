public class Clock {
    private int hour;
    private int minute;
    private int second;
    private boolean format24;

    public Clock() {
        this.format24 = true; 
    }

    public void Set(int hour, int minute, int second) {
        if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60 && second >= 0 && second < 60) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        } else {
            System.out.println("Invalid hour, minute or second value.");
        }
    }

    public void Write() {
        String hourFormat = format24 ? "hh:mm:ss" : "hh:mm:ss a";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(hourFormat);
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(0, 0, 0, this.hour, this.minute, this.second);
        System.out.println(sdf.format(calendar.getTime()));
    }

    public void format(boolean format24) {
        this.format24 = format24;
    }

    public void tick() {
        second++;
        if (second >= 60) {
            second = 0;
            minute++;
            if (minute >= 60) {
                minute = 0;
                hour++;
                if (hour >= 24) {
                    hour = 0;
                }
            }
        }
    }
}

