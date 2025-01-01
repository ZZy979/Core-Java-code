package preferences;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * A program to test preference settings. The program remembers the
 * frame position, size, and last selected file.
 * @version 1.10 2018-04-10
 * @author Cay Horstmann
 */
public class ImageViewer {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ImageViewerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
