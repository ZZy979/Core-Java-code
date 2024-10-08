package timer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * @version 1.02 2017-12-14
 * @author Cay Horstmann
 */
public class TimerTest {
    public static void main(String[] args) {
        var listener = new TimePrinter();

        // construct a timer that calls the listener once every second
        var timer = new Timer(1000, listener);
        timer.start();

        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("At the tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
        Toolkit.getDefaultToolkit().beep();
    }
}
