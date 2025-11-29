package numberFormat;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates formatting numbers under various locales.
 * @version 2.0 2021-09-24
 * @author Cay Horstmann
 */
public class NumberFormatTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new NumberFormatFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
