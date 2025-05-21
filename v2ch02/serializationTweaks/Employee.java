package serializationTweaks;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable, ObjectInputValidation {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee() {}

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    @Override
    public void validateObject() throws InvalidObjectException {
        if (salary < 0)
            throw new InvalidObjectException("salary < 0");
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.registerValidation(this, 0);
        in.defaultReadObject();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
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
