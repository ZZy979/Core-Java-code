package calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.35 2018-04-10
 * @author Cay Horstmann
 */
public class Calculator {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new CalculatorFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
