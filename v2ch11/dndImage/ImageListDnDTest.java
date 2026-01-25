package dndImage;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates drag and drop in an image list.
 * @version 1.02 2016-05-10
 * @author Cay Horstmann
 */
public class ImageListDnDTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ImageListDnDFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
