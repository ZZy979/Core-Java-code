package circularArrayQueue;

/**
 * This program demonstrates how to extend the collections framework.
 * @version 1.22 2018-04-10
 * @author Cay Horstmann
 */
public class CircularArrayQueueTest {
    public static void main(String[] args) {
        var q = new CircularArrayQueue<String>(5);
        q.add("Amy");
        q.add("Bob");
        q.add("Carl");
        q.add("Deedee");
        q.add("Emile");
        q.remove();
        q.add("Fifi");
        q.remove();
        for (String s : q)
            System.out.println(s);
    }
}
