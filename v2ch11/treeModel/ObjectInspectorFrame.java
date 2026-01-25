package treeModel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import serial.Employee;
import serial.Manager;

/**
 * This frame holds the object tree.
 */
public class ObjectInspectorFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    private JTree tree;

    public ObjectInspectorFrame() {
        setTitle("ObjectInspectorTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // we inspect this frame object
        // var v = new Variable(getClass(), "this", this);

        var harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        var carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        carl.setSecretary(harry);
        var v = new Variable(carl.getClass(), "carl", carl);

        var model = new ObjectTreeModel();
        model.setRoot(v);

        // construct and show tree
        tree = new JTree(model);
        add(new JScrollPane(tree), BorderLayout.CENTER);
    }
}
