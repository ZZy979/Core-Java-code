package clip;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClippingTestFrame extends JFrame {
    public ClippingTestFrame() {
        setTitle("ClippingTest");

        ClippingComponent canvas = new ClippingComponent();
        add(canvas, BorderLayout.CENTER);
        pack();
    }
}
