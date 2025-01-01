package font;

import javax.swing.JFrame;

/**
 * A frame with a text message component.
 */
public class FontFrame extends JFrame {
    public FontFrame() {
        setTitle("FontTest");
        add(new FontComponent());
        pack();
    }
}
