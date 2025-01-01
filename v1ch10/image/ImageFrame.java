package image;

import javax.swing.JFrame;

/**
 * A frame with an image component
 */
public class ImageFrame extends JFrame {
    public ImageFrame() {
        setTitle("ImageTest");
        add(new ImageComponent());
        pack();
    }
}
