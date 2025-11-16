/**
 *  @version 1.11 2007-10-26
 *  @author Cay Horstmann
 */
public class HelloNativeTest {
    public static void main(String[] args) {
        HelloNative.greeting();
    }

    static {
        System.loadLibrary("HelloNative");
    }
}
