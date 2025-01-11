package robot;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import button.ButtonFrame;

/**
 * @version 1.05 2015-08-20
 * @author Cay Horstmann
 */
public class RobotTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // make frame with a button panel
            var frame = new ButtonFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

        // attach a robot to the screen device
        var environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        var screen = environment.getDefaultScreenDevice();

        try {
            final var robot = new Robot(screen);
            robot.waitForIdle();
            new Thread(() -> runTest(robot)).start();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs a sample test procedure
     * @param robot the robot attached to the screen device
     */
    public static void runTest(Robot robot) {
        // simulate a space bar press
        robot.keyPress(' ');
        robot.keyRelease(' ');

        // simulate a tab key followed by a space
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(' ');
        robot.keyRelease(' ');

        // simulate a mouse click over the rightmost button
        robot.delay(2000);
        robot.mouseMove(220, 40);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        // capture the screen and show the resulting image
        robot.delay(2000);
        var image = robot.createScreenCapture(new Rectangle(0, 0, 400, 300));
        var frame = new ImageFrame(image);
        frame.setVisible(true);
    }
}
