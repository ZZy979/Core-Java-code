package toolBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import static util.ResourceUtil.getImageIcon;

/**
 * A frame with a toolbar and menu for color changes.
 */
public class ToolBarFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private JPanel panel;

    public ToolBarFrame() {
        setTitle("ToolBarTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // add a panel for color change
        panel = new JPanel();
        add(panel, BorderLayout.CENTER);

        // set up actions
        var blueAction = new ColorAction("Blue", getImageIcon("/blue-ball.gif"), Color.BLUE);
        var yellowAction = new ColorAction("Yellow", getImageIcon("/yellow-ball.gif"), Color.YELLOW);
        var redAction = new ColorAction("Red", getImageIcon("/red-ball.gif"), Color.RED);

        var exitAction = new AbstractAction("Exit", getImageIcon("/exit.gif")) {
            @Override
            public void actionPerformed(ActionEvent event) {
                // System.exit(0);
                ToolBarFrame.this.dispose();
            }
        };
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit");

        // populate toolbar
        var bar = new JToolBar();
        bar.add(blueAction);
        bar.add(yellowAction);
        bar.add(redAction);
        bar.addSeparator();
        bar.add(exitAction);
        add(bar, BorderLayout.NORTH);

        // populate menu
        var menu = new JMenu("Color");
        menu.add(blueAction);
        menu.add(yellowAction);
        menu.add(redAction);
        menu.addSeparator();
        menu.add(exitAction);

        var menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    /**
     * The color action sets the background of the frame to a given color.
     */
    public class ColorAction extends AbstractAction {
        public ColorAction(String name, Icon icon, Color c) {
            putValue(NAME, name);
            putValue(SMALL_ICON, icon);
            putValue(SHORT_DESCRIPTION, name + " background");
            putValue("color", c);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            var color = (Color) getValue("color");
            panel.setBackground(color);
        }
    }
}
