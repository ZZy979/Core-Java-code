package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static util.DBUtil.getConnection;

/**
 * This program tests that the database and the JDBC driver are correctly configured.
 * @version 1.04 2021-06-17
 * @author Cay Horstmann
 */
public class TestDB {
    public static void main(String[] args) throws IOException {
        try {
            runTest();
        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
    }

    /**
     * Runs a test by creating a table, adding a value, showing the table contents, and
     * removing the table.
     */
    public static void runTest() throws SQLException, IOException {
        try (Connection conn = getConnection();
                Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");

            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings")) {
                if (result.next())
                    System.out.println(result.getString(1));
            }
            stat.executeUpdate("DROP TABLE Greetings");
        }
    }
}
