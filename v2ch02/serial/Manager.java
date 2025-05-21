package serial;

public class Manager extends Employee {
    private Employee secretary;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        secretary = null;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return super.toString() + "[secretary=" + secretary + "]";
    }
}
