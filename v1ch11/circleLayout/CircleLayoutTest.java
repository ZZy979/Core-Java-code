package circleLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.34 2018-04-10
 * @author Cay Horstmann
 */
public class CircleLayoutTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new CircleLayoutFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
