package layoutManager;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * A frame with buttons managed by a border layout
 */
public class BorderLayoutFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public BorderLayoutFrame() {
        setTitle("BorderLayoutTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        add(new JButton("North"), BorderLayout.NORTH);
        add(new JButton("South"), BorderLayout.SOUTH);
        add(new JButton("East"), BorderLayout.EAST);
        add(new JButton("West"), BorderLayout.WEST);
        add(new JButton("Center"), BorderLayout.CENTER);
    }
}
