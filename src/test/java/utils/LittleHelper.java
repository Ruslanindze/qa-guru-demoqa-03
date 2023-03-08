package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class LittleHelper {

    public static String[] monthNames = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    public static Map<String, String> getMapBirthdayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        var map = Map.of(
                "day", Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)),
                "month", monthNames[calendar.get(Calendar.MONTH)],
                "year", Integer.toString(calendar.get(Calendar.YEAR))
        );

        return map;
    }
}
