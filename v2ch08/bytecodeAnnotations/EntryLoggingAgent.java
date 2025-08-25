package bytecodeAnnotations;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @version 1.11 2018-05-01
 * @author Cay Horstmann
 */
public class EntryLoggingAgent {
    public static void premain(String arg, Instrumentation instr) {
        instr.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> cl,
                    ProtectionDomain pd, byte[] data) throws IllegalClassFormatException {
                if (!className.replace("/", ".").equals(arg))
                    return null;
                var reader = new ClassReader(data);
                var writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                var el = new EntryLogger(writer, className);
                reader.accept(el, ClassReader.EXPAND_FRAMES);
                return writer.toByteArray();
            }
        });
    }
}
