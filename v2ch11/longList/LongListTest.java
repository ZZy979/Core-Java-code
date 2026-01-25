package longList;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates a list that dynamically computes list entries.
 * @version 1.24 2016-05-10
 * @author Cay Horstmann
 */
public class LongListTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new LongListFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
