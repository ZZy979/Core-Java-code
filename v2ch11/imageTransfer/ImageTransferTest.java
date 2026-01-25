package imageTransfer;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the transfer of images between a Java application and the system
 * clipboard.
 * @version 1.23 2016-05-10
 * @author Cay Horstmann
 */
public class ImageTransferTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ImageTransferFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
