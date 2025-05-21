package serializationTweaks;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

public class LabeledPoint implements Serializable {
    private String label;
    private transient Point2D.Double point;

    public LabeledPoint(String label, double x, double y) {
        this.label = label;
        this.point = new Point2D.Double(x, y);
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeDouble(point.getX());
        out.writeDouble(point.getY());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        double x = in.readDouble();
        double y = in.readDouble();
        point = new Point2D.Double(x, y);
    }

    public String getLabel() {
        return label;
    }

    public Point2D.Double getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "%s[label=%s,point=%s]".formatted(getClass().getName(), label, point);
    }
}
