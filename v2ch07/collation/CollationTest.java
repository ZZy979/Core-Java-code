package collation;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates collating strings under various locales.
 * @version 1.16 2018-05-01
 * @author Cay Horstmann
 */
public class CollationTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new CollationFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
