package print;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates how to print 2D graphics
 * @version 1.14 2018-05-01
 * @author Cay Horstmann
 */
public class PrintTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new PrintTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
