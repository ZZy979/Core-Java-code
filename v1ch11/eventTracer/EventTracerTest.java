package eventTracer;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.14 2015-08-20
 * @author Cay Horstmann
 */
public class EventTracerTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new EventTracerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
