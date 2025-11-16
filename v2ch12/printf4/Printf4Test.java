import java.io.PrintWriter;

/**
 * @version 1.11 2018-05-01
 * @author Cay Horstmann
 */
public class Printf4Test {
    public static void main(String[] args) {
        try {
            double price = 44.95;
            double tax = 7.75;
            double amountDue = price * (1 + tax / 100);
            var out = new PrintWriter(System.out);
            /* This call will throw an exception--note the %% */
            Printf4.fprint(out, "Amount due = %%8.2f\n", amountDue);
            out.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
