package notHelloWorld;

import javax.swing.JFrame;

/**
 * A frame that contains a message panel.
 */
public class NotHelloWorldFrame extends JFrame {
    public NotHelloWorldFrame() {
        setTitle("NotHelloWorld");
        add(new NotHelloWorldComponent());
        pack();
    }
}
