package classLoader;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates a custom class loader that decrypts class files.
 * @version 1.25 2018-05-01
 * @author Cay Horstmann
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ClassLoaderFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
