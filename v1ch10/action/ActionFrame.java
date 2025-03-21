package action;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import static util.ResourceUtil.getImageIcon;

/**
 * A frame with a panel that demonstrates color change actions.
 */
public class ActionFrame extends JFrame {
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ActionFrame() {
        setTitle("ActionTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        buttonPanel = new JPanel();

        // define actions
        var yellowAction = new ColorAction("Yellow", getImageIcon("/yellow-ball.gif"), Color.YELLOW);
        var blueAction = new ColorAction("Blue", getImageIcon("/blue-ball.gif"), Color.BLUE);
        var redAction = new ColorAction("Red", getImageIcon("/red-ball.gif"), Color.RED);

        // add buttons for these actions
        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        buttonPanel.add(new JButton(redAction));

        // add panel to frame
        add(buttonPanel);

        // associate the Y, B, and R keys with names
        InputMap inputMap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        inputMap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        inputMap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");

        // associate the names with actions
        ActionMap actionMap = buttonPanel.getActionMap();
        actionMap.put("panel.yellow", yellowAction);
        actionMap.put("panel.blue", blueAction);
        actionMap.put("panel.red", redAction);
    }

    public class ColorAction extends AbstractAction {
        /**
         * Constructs a color action.
         * @param name the name to show on the button
         * @param icon the icon to display on the button
         * @param c the background color
         */
        public ColorAction(String name, Icon icon, Color c) {
            putValue(NAME, name);
            putValue(SMALL_ICON, icon);
            putValue(SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
            putValue("color", c);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            var color = (Color) getValue("color");
            buttonPanel.setBackground(color);
        }
    }
}
