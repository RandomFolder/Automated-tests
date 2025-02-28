package task5.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeHandler {
    public static Timestamp addTime(Timestamp time, int valueInSeconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time.getTime());
        cal.add(Calendar.SECOND, valueInSeconds);

        return new Timestamp(cal.getTime().getTime());
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
