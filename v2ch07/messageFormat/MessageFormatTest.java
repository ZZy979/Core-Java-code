package messageFormat;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MessageFormatTest {
    public static void main(String[] args) {
        String pattern = "On {2,date,long}, {0} destroyed {1,choice,0#no houses|1#one house|2#{1} houses}"
                + " and caused {3,number,currency} of damage.";
        Locale loc = Locale.US;
        var mf = new MessageFormat(pattern, loc);
        String msg = mf.format(new Object[] {"hurricane", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0E8});
        System.out.println(msg);
    }
}
