package serializationTweaks;

import java.awt.Color;
import java.awt.Point;
import java.io.Serial;
import java.io.Serializable;

public class ColoredPoint implements Serializable {
    private Color color;
    private Point location;

    public ColoredPoint(Color color, int x, int y) {
        this.color = color;
        this.location = new Point(x, y);
    }

    @Serial
    private Object writeReplace() {
        return new Ser(color.getRGB(), location.x, location.y);
    }

    private record Ser(int rgba, int x, int y) implements Serializable {
        @Serial
        private Object readResolve() {
            return new ColoredPoint(new Color(rgba), x, y);
        }
    }

    @Override
    public String toString() {
        return "%s[color=%s,location=%s]".formatted(getClass().getName(), color, location);
    }
}
