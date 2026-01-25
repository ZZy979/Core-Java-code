package splashScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SplashScreen;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import static util.ResourceUtil.getImageIcon;

/**
 * This program demonstrates the splash screen API.
 * @version 1.01 2016-05-10
 * @author Cay Horstmann
 */
/*
 * Use the -splash command-line option:
 *     java -splash:myimage.png MyApp
 * Or specify it in the manifest of a JAR file
 *     Main-Class: MyApp
 *     SplashScreen-Image: myimage.gif
 */
public class SplashScreenTest {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    private static SplashScreen splash;

    private static void drawOnSplash(int percent) {
        Rectangle bounds = splash.getBounds();
        Graphics2D g = splash.createGraphics();
        int height = 20;
        int x = 2;
        int y = bounds.height - height / 2;
        int width = bounds.width - 4;
        Color brightPurple = new Color(76, 36, 121);
        g.setColor(brightPurple);
        g.fillRect(x, y, width * percent / 100, height);
        splash.update();
    }

    /**
     * This method draws on the splash screen.
     */
    private static void init1() {
        splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.err.println("Did you specify a splash image with -splash or in the manifest?");
            System.exit(1);
        }

        try {
            for (int i = 0; i <= 100; i++) {
                drawOnSplash(i);
                Thread.sleep(100);  // simulate startup work
            }
        }
        catch (InterruptedException e) {
        }
    }

    /**
     * This method displays a frame with the same image as the splash screen.
     */
    private static void init2() {
        Image img = getImageIcon("/splashScreen/splash.png").getImage();

        JFrame splashFrame = new JFrame();
        splashFrame.setUndecorated(true);

        JPanel splashPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, null);
            }
        };

        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        splashPanel.setLayout(new BorderLayout());
        splashPanel.add(progressBar, BorderLayout.SOUTH);

        splashFrame.add(splashPanel);
        splashFrame.setBounds(splash.getBounds());
        splashFrame.setVisible(true);

        new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    for (int i = 0; i <= 100; i++) {
                        publish(i);
                        Thread.sleep(100);
                    }
                }
                catch (InterruptedException e) {
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                for (Integer chunk : chunks) {
                    progressBar.setString("Loading module " + chunk);
                    progressBar.setValue(chunk);
                    splashPanel.repaint();  // because img is loaded asynchronously
                }
            }

            @Override
            protected void done() {
                splashFrame.setVisible(false);

                JFrame frame = new JFrame();
                frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("SplashScreenTest");
                frame.setVisible(true);
            }
        }.execute();
    }

    public static void main(String[] args) {
        init1();
        EventQueue.invokeLater(() -> init2());
    }
}
