package sizedFrame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SizedFrame extends JFrame {
    public SizedFrame() {
        setTitle("SizedFrame");

        // get screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // set frame width, height and let platform pick screen location
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);

        // set frame icon
        var url = getClass().getResource("/icon.gif");
        if (url != null) {
            Image img = new ImageIcon(url).getImage();
            setIconImage(img);
        }
    }
}
