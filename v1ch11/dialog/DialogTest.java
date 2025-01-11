package dialog;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.35 2018-04-10
 * @author Cay Horstmann
 */
public class DialogTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new DialogFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
