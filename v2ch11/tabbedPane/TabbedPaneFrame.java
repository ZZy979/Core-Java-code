package tabbedPane;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import static util.ResourceUtil.getImageIcon;

/**
 * This frame shows a tabbed pane and radio buttons to switch between wrapped and scrolling tab
 * layout.
 */
public class TabbedPaneFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    private JTabbedPane tabbedPane;

    public TabbedPaneFrame() {
        setTitle("TabbedPaneTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        tabbedPane = new JTabbedPane();
        // we set the components to null and delay their loading until the tab is shown
        // for the first time
        ImageIcon icon = getImageIcon("/yellow-ball.gif");

        tabbedPane.addTab("Mercury", icon, null);
        tabbedPane.addTab("Venus", icon, null);
        tabbedPane.addTab("Earth", icon, null);
        tabbedPane.addTab("Mars", icon, null);
        tabbedPane.addTab("Jupiter", icon, null);
        tabbedPane.addTab("Saturn", icon, null);
        tabbedPane.addTab("Uranus", icon, null);
        tabbedPane.addTab("Neptune", icon, null);
        tabbedPane.addTab("Pluto", null, null);

        int plutoIndex = tabbedPane.indexOfTab("Pluto");
        JPanel plutoPanel = new JPanel();
        plutoPanel.add(new JLabel("Pluto", icon, SwingConstants.LEADING));
        JToggleButton plutoCheckBox = new JCheckBox();
        plutoCheckBox.addActionListener(event -> tabbedPane.remove(plutoIndex));
        plutoPanel.add(plutoCheckBox);
        tabbedPane.setTabComponentAt(plutoIndex, plutoPanel);

        add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addChangeListener(event -> {
            // check if this tab still has a null component
            if (tabbedPane.getSelectedComponent() == null) {
                // set the component to the image icon
                int n = tabbedPane.getSelectedIndex();
                loadTab(n);
            }
        });

        loadTab(0);

        JPanel buttonPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton wrapButton = new JRadioButton("Wrap tabs");
        wrapButton.addActionListener(event -> tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT));
        buttonPanel.add(wrapButton);
        buttonGroup.add(wrapButton);
        wrapButton.setSelected(true);
        JRadioButton scrollButton = new JRadioButton("Scroll tabs");
        scrollButton.addActionListener(event -> tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT));
        buttonPanel.add(scrollButton);
        buttonGroup.add(scrollButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Loads the tab with the given index.
     * @param n the index of the tab to load
     */
    private void loadTab(int n) {
        String title = tabbedPane.getTitleAt(n);
        ImageIcon planetIcon = getImageIcon("/" + title + ".gif");
        tabbedPane.setComponentAt(n, new JLabel(planetIcon));

        // indicate that this tab has been visited--just for fun
        tabbedPane.setIconAt(n, getImageIcon("/red-ball.gif"));
    }
}
