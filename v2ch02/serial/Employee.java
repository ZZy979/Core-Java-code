package serial;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 8367346051156850807L;

    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {}

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        var calendar = new GregorianCalendar(year, month - 1, day);
        this.hireDay = calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
}
