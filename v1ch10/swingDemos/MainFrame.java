package swingDemos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import action.ActionFrame;
import button.ButtonFrame;
import draw.DrawFrame;
import fill.FillFrame;
import font.FontFrame;
import image.ImageFrame;
import mouse.MouseFrame;
import notHelloWorld.NotHelloWorldFrame;
import plaf.PlafFrame;
import preferences.ImageViewerFrame;
import simpleFrame.SimpleFrame;
import sizedFrame.SizedFrame;

/**
 * Main frame of Swing Demos Collection
 * @author ZZy
 */
public class MainFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 800;

    private JPanel buttonPanel;

    public MainFrame() {
        setTitle("Swing Demos Collection");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        var demos = new LinkedHashMap<String, Class<? extends JFrame>>();
        demos.put("Hello World", SimpleFrame.class);
        demos.put("Get screen size & set icon", SizedFrame.class);
        demos.put("Draw string", NotHelloWorldFrame.class);
        demos.put("Draw geometric shapes", DrawFrame.class);
        demos.put("Fill geometric shapes", FillFrame.class);
        demos.put("Draw baseline and string bounds", FontFrame.class);
        demos.put("Display a tiled image", ImageFrame.class);
        demos.put("Change background color (Event handling)", ButtonFrame.class);
        demos.put("Change look-and-feel", PlafFrame.class);
        demos.put("Change background color (Swing actions)", ActionFrame.class);
        demos.put("Simple graphics editor (Mouse events)", MouseFrame.class);
        demos.put("Image viewer (Preferences API)", ImageViewerFrame.class);

        JLabel label = new JLabel(getTitle());
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(demos.size(), 1, 5, 5));
        demos.forEach(this::addButton);
        add(buttonPanel);

        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addButton(String name, Class<? extends JFrame> clazz) {
        JButton button = new JButton(name);
        button.addActionListener(event -> {
            try {
                JFrame frame = clazz.getDeclaredConstructor().newInstance();
                frame.setVisible(true);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        buttonPanel.add(button);
    }
}
