package exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static util.DBUtil.getConnection;

/**
 * Executes all SQL statements in a file. Call this program as <br>
 * java -classpath driverPath:. ExecSQL commandFile
 *
 * @version 1.34 2021-06-17
 * @author Cay Horstmann
 */
public class ExecSQL {
    public static void main(String[] args) throws IOException {
        try (Scanner in = args.length == 0 ? new Scanner(System.in)
                : new Scanner(Path.of(args[0]), StandardCharsets.UTF_8)) {
            try (Connection conn = getConnection();
                    Statement stat = conn.createStatement()) {
                while (true) {
                    if (args.length == 0)
                        System.out.println("Enter command or EXIT to exit:");
                    if (!in.hasNextLine()) return;

                    String line = in.nextLine().strip();
                    if (line.equalsIgnoreCase("EXIT")) return;
                    if (line.endsWith(";"))  // remove trailing semicolon
                        line = line.substring(0, line.length() - 1);
                    try {
                        boolean isResult = stat.execute(line);
                        if (isResult) {
                            try (ResultSet rs = stat.getResultSet()) {
                                showResultSet(rs);
                            }
                        }
                        else {
                            int updateCount = stat.getUpdateCount();
                            System.out.println(updateCount + " rows updated");
                        }
                    }
                    catch (SQLException e) {
                        for (Throwable t : e)
                            t.printStackTrace();
                    }
                }
            }
            catch (SQLException e) {
                for (Throwable t : e)
                    t.printStackTrace();
            }
        }
    }

    /**
     * Prints a result set.
     * @param result the result set to be printed
     */
    public static void showResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) System.out.print(", ");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) System.out.print(", ");
                System.out.print(result.getString(i));
            }
            System.out.println();
        }
    }
}
