package serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TimeZone;

/**
 * @version 1.12 2021-09-10
 * @author Cay Horstmann
 */
public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        var harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        var carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        carl.setSecretary(harry);
        var tony = new Manager("Tony Tester", 40000, 1990, 3, 15);
        tony.setSecretary(harry);

        var staff = new Employee[] {carl, harry, tony};

        // save all employee records to the file employee.ser
        try (var out = new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
            out.writeObject(staff);
        }

        // retrieve all records into a new array
        try (var in = new ObjectInputStream(new FileInputStream("employee.ser"))) {
            var newStaff = (Employee[]) in.readObject();

            // raise secretary's salary
            newStaff[1].raiseSalary(10);

            // print the newly read employee records
            for (Employee e : newStaff)
                System.out.println(e);
        }
    }
}
