package serialClone;

/**
 * @version 1.22 2018-05-01
 * @author Cay Horstmann
 */
public class SerialCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        var harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
        // clone harry
        var harryClone = (Employee) harry.clone();

        // mutate harry
        harry.raiseSalary(10);

        // now harry and the clone are different
        System.out.println(harry);
        System.out.println(harryClone);
    }
}
