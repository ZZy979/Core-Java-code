package area;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates different constructive area geometry operations.
 * @author ZZy
 */
public class AreaTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new AreaTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
