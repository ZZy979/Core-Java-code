package border;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.34 2015-06-13
 * @author Cay Horstmann
 */
public class BorderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new BorderFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
