package randomAccess;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

import equals.Employee;

/**
 * @version 1.14 2018-05-01
 * @author Cay Horstmann
 */
public class RandomAccessTest {
    public static final int NAME_SIZE = 40;
    public static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;

    public static void main(String[] args) throws IOException {
        var staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try (var out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
            // save all employee records to the file employee.dat
            for (Employee e : staff)
                writeData(out, e);
        }

        try (var in = new RandomAccessFile("employee.dat", "r")) {
            // retrieve all records into a new array

            // compute the array size
            int n = (int) (in.length() / RECORD_SIZE);
            var newStaff = new Employee[n];

            // read employees in reverse order
            for (int i = n - 1; i >= 0; i--) {
                in.seek(i * RECORD_SIZE);
                newStaff[i] = readData(in);
            }

            // print the newly read employee records
            for (Employee e : newStaff)
                System.out.println(e);
        }
    }

    /**
     * Writes employee data to a data output
     * @param out the data output
     * @param e the employee
     */
    public static void writeData(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), NAME_SIZE, out);
        out.writeDouble(e.getSalary());

        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    /**
     * Reads employee data from a data input
     * @param in the data input
     * @return the employee
     */
    public static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m, d);
    }
}
