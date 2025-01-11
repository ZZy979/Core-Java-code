package colorChooser;

import javax.swing.JFrame;

/**
 * A frame with a color chooser panel
 */
public class ColorChooserFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ColorChooserFrame() {
        setTitle("ColorChooserTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // add color chooser panel to frame
        var panel = new ColorChooserPanel();
        add(panel);
    }
}
