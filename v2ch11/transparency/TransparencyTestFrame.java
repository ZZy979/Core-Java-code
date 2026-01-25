package transparency;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TransparencyTestFrame extends JFrame {
    public TransparencyTestFrame() {
        setTitle("TransparencyTest");

        var canvas = new TransparencyComponent();
        add(canvas, BorderLayout.CENTER);
        pack();
    }
}
