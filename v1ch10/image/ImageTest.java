package image;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.34 2015-05-12
 * @author Cay Horstmann
 */
public class ImageTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ImageFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
