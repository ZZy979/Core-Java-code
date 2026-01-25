package renderQuality;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import gridbag.GBC;

/**
 * This frame contains buttons to set rendering hints and an image that is drawn with the selected
 * hints.
 */
public class RenderQualityTestFrame extends JFrame {
    private RenderQualityComponent canvas;
    private JPanel buttonBox;
    private RenderingHints hints;
    private int r;

    public RenderQualityTestFrame() {
        setTitle("RenderQualityTest");

        buttonBox = new JPanel();
        buttonBox.setLayout(new GridBagLayout());
        hints = new RenderingHints(null);

        makeButtons("KEY_ANTIALIASING", "VALUE_ANTIALIAS_OFF", "VALUE_ANTIALIAS_ON");
        makeButtons("KEY_TEXT_ANTIALIASING", "VALUE_TEXT_ANTIALIAS_OFF", "VALUE_TEXT_ANTIALIAS_ON");
        makeButtons("KEY_FRACTIONALMETRICS", "VALUE_FRACTIONALMETRICS_OFF", "VALUE_FRACTIONALMETRICS_ON");
        makeButtons("KEY_RENDERING", "VALUE_RENDER_SPEED", "VALUE_RENDER_QUALITY");
        makeButtons("KEY_STROKE_CONTROL", "VALUE_STROKE_PURE", "VALUE_STROKE_NORMALIZE");

        canvas = new RenderQualityComponent();
        canvas.setRenderingHints(hints);

        add(canvas, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.NORTH);
        pack();
    }

    /**
     * Makes a set of buttons for a rendering hint key and values.
     * @param key the key name
     * @param value1 the name of the first value for the key
     * @param value2 the name of the second value for the key
     */
    private void makeButtons(String key, String value1, String value2) {
        try {
            RenderingHints.Key k = (RenderingHints.Key) RenderingHints.class.getField(key).get(null);
            Object v1 = RenderingHints.class.getField(value1).get(null);
            Object v2 = RenderingHints.class.getField(value2).get(null);
            JLabel label = new JLabel(key);
            buttonBox.add(label, new GBC(0, r).setAnchor(GBC.WEST));

            ButtonGroup group = new ButtonGroup();
            JRadioButton b1 = new JRadioButton(value1, true);
            buttonBox.add(b1, new GBC(1, r).setAnchor(GBC.WEST));
            group.add(b1);
            b1.addActionListener(event -> {
                hints.put(k, v1);
                canvas.setRenderingHints(hints);
            });

            JRadioButton b2 = new JRadioButton(value2, false);
            buttonBox.add(b2, new GBC(2, r).setAnchor(GBC.WEST));
            group.add(b2);
            b2.addActionListener(event -> {
                hints.put(k, v2);
                canvas.setRenderingHints(hints);
            });

            hints.put(k, v1);
            r++;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
