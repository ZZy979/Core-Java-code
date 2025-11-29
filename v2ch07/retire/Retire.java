package retire;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program shows a retirement calculator. The UI is displayed in English, German, and
 * Chinese.
 * @version 1.25 2018-05-01
 * @author Cay Horstmann
 */
public class Retire {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new RetireFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
