package dateFormat;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates formatting dates under various locales.
 * @version 1.01 2018-05-01
 * @author Cay Horstmann
 */
public class DateTimeFormatTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new DateTimeFormatterFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
