package collecting;

import java.util.Locale;

public class Util {
    public static Locale[] getAvailableLocales() {
        return new Locale[] {
                Locale.FRANCE, Locale.GERMANY, Locale.ITALY, Locale.JAPAN, Locale.KOREA,
                Locale.UK, Locale.US, Locale.CANADA, Locale.CANADA_FRENCH, Locale.CHINA, Locale.TAIWAN,
                new Locale("fr", "CH"), new Locale("de", "CH"), new Locale("it", "CH")
        };
    }
}
