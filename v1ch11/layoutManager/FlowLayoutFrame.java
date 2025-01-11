package layoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A frame with a panel with six buttons managed by a flow layout
 */
public class FlowLayoutFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public FlowLayoutFrame() {
        setTitle("FlowLayoutTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Yellow"));
        buttonPanel.add(new JButton("Blue"));
        buttonPanel.add(new JButton("Red"));
        buttonPanel.add(new JButton("Green"));
        buttonPanel.add(new JButton("Orange"));
        buttonPanel.add(new JButton("Fuchsia"));

        add(buttonPanel);
    }
}
