package swingDemos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import action.ActionFrame;
import border.BorderFrame;
import button.ButtonFrame;
import calculator.CalculatorFrame;
import checkBox.CheckBoxFrame;
import circleLayout.CircleLayoutFrame;
import colorChooser.ColorChooserFrame;
import comboBox.ComboBoxFrame;
import dataExchange.DataExchangeFrame;
import dialog.DialogFrame;
import draw.DrawFrame;
import eventTracer.EventTracerFrame;
import fill.FillFrame;
import image.ImageFrame;
import layoutManager.BorderLayoutFrame;
import layoutManager.FlowLayoutFrame;
import menu.MenuFrame;
import mouse.MouseFrame;
import notHelloWorld.NotHelloWorldFrame;
import optionDialog.OptionDialogFrame;
import plaf.PlafFrame;
import radioButton.RadioButtonFrame;
import simpleFrame.SimpleFrame;
import sizedFrame.SizedFrame;
import slider.SliderFrame;
import text.TextComponentFrame;
import toolBar.ToolBarFrame;

/**
 * Main frame of Swing Demos Collection
 * @author ZZy
 */
public class MainFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 600;

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
        demos.put("Draw baseline and string bounds", font.FontFrame.class);
        demos.put("Display a tiled image", ImageFrame.class);
        demos.put("Change background color (Event handling)", ButtonFrame.class);
        demos.put("Change look-and-feel", PlafFrame.class);
        demos.put("Change background color (Swing actions)", ActionFrame.class);
        demos.put("Simple graphics editor (Mouse events)", MouseFrame.class);
        demos.put("Image viewer (Preferences API)", preferences.ImageViewerFrame.class);
        demos.put("Layout manager (Flow layout)", FlowLayoutFrame.class);
        demos.put("Layout manager (Border layout)", BorderLayoutFrame.class);
        demos.put("Simple calculator (Grid layout)", CalculatorFrame.class);
        demos.put("Text components", TextComponentFrame.class);
        demos.put("Checkboxes", CheckBoxFrame.class);
        demos.put("Radio Buttons", RadioButtonFrame.class);
        demos.put("Borders", BorderFrame.class);
        demos.put("Combo boxes", ComboBoxFrame.class);
        demos.put("Sliders", SliderFrame.class);
        demos.put("Menus", MenuFrame.class);
        demos.put("Toolbars", ToolBarFrame.class);
        demos.put("Font selector (Grid bag layout)", gridbag.FontFrame.class);
        demos.put("Custom layout manager", CircleLayoutFrame.class);
        demos.put("Option dialogs", OptionDialogFrame.class);
        demos.put("Custom dialogs", DialogFrame.class);
        demos.put("Login dialog (Data exchange)", DataExchangeFrame.class);
        demos.put("Image viewer (File chooser dialogs)", fileChooser.ImageViewerFrame.class);
        demos.put("Color chooser dialogs", ColorChooserFrame.class);
        demos.put("Event tracer", EventTracerFrame.class);

        JLabel label = new JLabel(getTitle());
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(demos.size(), 1, 1, 1));
        demos.forEach(this::addButton);
        add(new JScrollPane(buttonPanel), BorderLayout.CENTER);

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
