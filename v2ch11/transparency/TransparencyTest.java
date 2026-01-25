package transparency;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TransparencyTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new TransparencyTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
