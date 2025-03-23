package formatting;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @version 1.0 2016-05-10
 * @author Cay Horstmann
 */
public class Formatting {
    public static void main(String[] args) {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        String formatted = formatter.format(apollo11launch);
        System.out.println("Standard formatter ISO_OFFSET_DATE_TIME: " + formatted);

        Locale.setDefault(Locale.US);
        formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        formatted = formatter.format(apollo11launch);
        System.out.println("Locale-specific formatter (US): " + formatted);
        formatted = formatter.withLocale(Locale.FRENCH).format(apollo11launch);
        System.out.println("Locale-specific formatter (French): " + formatted);

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted = formatter.format(apollo11launch);
        System.out.println("Formatter with custom pattern: " + formatted);

        LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
        System.out.println("churchsBirthday: " + churchsBirthday);
        apollo11launch = ZonedDateTime.parse(
                "1969-07-16 03:32:00-0400", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        System.out.println("apollo11launch: " + apollo11launch);

        for (DayOfWeek w : DayOfWeek.values())
            System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ");
    }
}
