package bounceThread;

import bounce.Ball;

/**
 * The frame with ball component and buttons.
 */
public class BounceFrame extends bounce.BounceFrame {
    public BounceFrame() {
        super();
        setTitle("BounceThread");
    }

    /**
     * Adds a bouncing ball to the canvas and starts a thread to make it bounce
     */
    @Override
    public void addBall() {
        var ball = new Ball();
        comp.add(ball);
        Runnable r = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    ball.move(comp.getBounds());
                    comp.repaint();
                    Thread.sleep(DELAY);
                }
            }
            catch (InterruptedException e) {
            }
        };
        var t = new Thread(r);
        t.start();
    }
}
