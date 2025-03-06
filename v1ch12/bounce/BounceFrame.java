package bounce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The frame with ball component and buttons.
 */
public class BounceFrame extends JFrame {
    protected BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    /**
     * Constructs the frame with the component for showing the bouncing ball and
     * Start and Close buttons
     */
    public BounceFrame() {
        setTitle("Bounce");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);

        var buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Close", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Adds a button to a container.
     * @param c the container
     * @param title the button title
     * @param listener the action listener for the button
     */
    public void addButton(Container c, String title, ActionListener listener) {
        var button = new JButton(title);
        button.addActionListener(listener);
        c.add(button);
    }

    /**
     * Adds a bouncing ball to the panel and makes it bounce 1,000 times.
     */
    public void addBall() {
        try {
            var ball = new Ball();
            comp.add(ball);

            for (int i = 0; i < STEPS; i++) {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException e) {
        }
    }
}
