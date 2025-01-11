package fileChooser;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.26 2018-04-10
 * @author Cay Horstmann
 */
public class FileChooserTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ImageViewerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
