package serializationTweaks;

import java.awt.Point;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class LabeledPixel extends Point implements Externalizable {
    private String label;

    public LabeledPixel() {} // required for externalizable class

    public LabeledPixel(String label, int x, int y) {
        super(x, y);
        this.label = label;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt((int) getX());
        out.writeInt((int) getY());
        out.writeUTF(label);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        int x = in.readInt();
        int y = in.readInt();
        setLocation(x, y);
        label = in.readUTF();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "%s[label=%s]".formatted(super.toString(), label);
    }
}
