package comparator;

import java.util.Arrays;

import static java.util.Comparator.*;

public class ComparatorTest {
    public static void main(String[] args) {
        Person[] people = {
                new Person("John", null, "Doe"),
                new Person("Jane", "A.", "Smith"),
                new Person("Alice", "B.", "Johnson"),
                new Person("Bob", "C.", "Doe")
        };

        // 1. sort by name
        Arrays.sort(people, comparing(Person::getName));
        System.out.println("Sort by name:\n" + Arrays.toString(people));

        // 2. sort by last name, then first name
        Arrays.sort(people, comparing(Person::lastName).thenComparing(Person::firstName));
        System.out.println("Sort by last name, then first name:\n" + Arrays.toString(people));

        // 3. sort by length of name
        Arrays.sort(people, comparingInt(p -> p.getName().length()));
        System.out.println("Sort by length of name:\n" + Arrays.toString(people));

        // 4. sort by middle name, null first
        Arrays.sort(people, comparing(Person::middleName, nullsFirst(naturalOrder())));
        System.out.println("Sort by middle name, null first:\n" + Arrays.toString(people));

        // 5. sort by name in reversed order
        Arrays.sort(people, comparing(Person::getName).reversed());
        System.out.println("Sort by name in reversed order:\n" + Arrays.toString(people));
    }
}

record Person(String firstName, String middleName, String lastName) {
    public String getName() {
        return firstName + " " + (middleName != null ? middleName + " " : "") + lastName;
    }

    @Override
    public String toString() {
        return getName();
    }
}
