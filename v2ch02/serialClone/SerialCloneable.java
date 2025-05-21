package serialClone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A class whose clone method uses serialization.
 */
public class SerialCloneable implements Cloneable, Serializable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            // save the object to a byte array
            var bout = new ByteArrayOutputStream();
            try (var out = new ObjectOutputStream(bout)) {
                out.writeObject(this);
            }

            // read a clone of the object from the byte array
            try (var bin = new ByteArrayInputStream(bout.toByteArray())) {
                var in = new ObjectInputStream(bin);
                return in.readObject();
            }
        }
        catch (IOException | ClassNotFoundException e) {
            var e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}
