package action;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.34 2015-06-12
 * @author Cay Horstmann
 */
public class ActionTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ActionFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
