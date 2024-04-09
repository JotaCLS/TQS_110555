package TQS.Homework.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {
    private StringToDate() {
        throw new IllegalStateException("Utility class");
    }
    public static Date parseStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }
}
