package slider;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.16 2018-04-10
 * @author Cay Horstmann
 */
public class SliderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new SliderFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
