package classLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class loader loads encrypted class files.
 */
public class CryptoClassLoader extends ClassLoader {
    private int key;

    /**
     * Constructs a crypto class loader.
     * @param key the decryption key
     */
    public CryptoClassLoader(int key) {
        this.key = key;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes = loadClassBytes(name);
            Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
            if (cl == null)
                throw new ClassNotFoundException(name);
            return cl;
        }
        catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    /**
     * Loads and decrypt the class file bytes.
     * @param name the class name
     * @return an array with the class file bytes
     */
    private byte[] loadClassBytes(String name) throws IOException {
        String cname = name.replace('.', '/') + ".caesar";
        byte[] bytes = Files.readAllBytes(Path.of(cname));
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (byte) (bytes[i] - key);
        return bytes;
    }
}
