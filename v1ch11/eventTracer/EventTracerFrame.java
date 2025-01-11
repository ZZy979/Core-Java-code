package eventTracer;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class EventTracerFrame extends JFrame {
    public EventTracerFrame() {
        setTitle("EventTracerTest");

        // add a slider and a button
        add(new JSlider(), BorderLayout.NORTH);
        add(new JButton("Test"), BorderLayout.SOUTH);

        // trap all events of components inside the frame
        EventTracer tracer = new EventTracer();
        tracer.add(this);
        pack();
    }
}
