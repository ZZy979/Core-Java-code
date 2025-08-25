package compiler;

import java.awt.EventQueue;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @version 1.10 2018-05-01
 * @author Cay Horstmann
 */
public class CompilerTest {
    public static void main(String[] args) throws IOException, ReflectiveOperationException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        var classFileObjects = new ArrayList<ByteArrayClass>();
        var diagnostics = new DiagnosticCollector<JavaFileObject>();

        StandardJavaFileManager stdFileManager = compiler.getStandardFileManager(diagnostics, null, null);
        JavaFileManager fileManager = new ForwardingJavaFileManager<JavaFileManager>(stdFileManager) {
            @Override
            public JavaFileObject getJavaFileForOutput(
                    Location location, String className, Kind kind, FileObject sibling) throws IOException {
                if (kind == Kind.CLASS) {
                    var fileObject = new ByteArrayClass(className);
                    classFileObjects.add(fileObject);
                    return fileObject;
                }
                else
                    return super.getJavaFileForOutput(location, className, kind, sibling);
            }
        };

        String frameClassName = args.length == 0 ? "buttons2.ButtonFrame" : args[0];
        JavaFileObject source = buildSource(frameClassName);
        CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, List.of(source));
        Boolean result = task.call();

        for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics())
            System.out.println(d.getKind() + ": " + d.getMessage(null));
        fileManager.close();
        if (!result) {
            System.out.println("Compilation failed.");
            System.exit(1);
        }

        var loader = new ByteArrayClassLoader(classFileObjects);
        var frame = (JFrame) loader.loadClass("x.Frame").getConstructor().newInstance();

        EventQueue.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("CompilerTest");
            frame.setVisible(true);
        });
    }

    /**
     * Builds the source for the subclass that implements the addEventHandlers method.
     * @return a file object containing the source in a string builder
     */
    public static JavaFileObject buildSource(String superClassName) throws IOException {
        var builder = new StringBuilder();
        builder.append("package x;\n\n");
        builder.append("public class Frame extends " + superClassName + " {\n");
        builder.append("protected void addEventHandlers() {\n");
        var props = new Properties();
        props.load(Files.newBufferedReader(
                Path.of(superClassName.replace('.', '/')).getParent().resolve("action.properties"),
                StandardCharsets.UTF_8));
        for (Map.Entry<Object, Object> e : props.entrySet()) {
            var beanName = (String) e.getKey();
            var eventCode = (String) e.getValue();
            builder.append(beanName + ".addActionListener(event -> {\n");
            builder.append(eventCode);
            builder.append("\n});\n");
        }
        builder.append("} }\n");
        return new StringSource("x.Frame", builder.toString());
    }
}
