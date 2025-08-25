package compiler;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class StringSource extends SimpleJavaFileObject {
    private String code;

    public StringSource(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + ".java"), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}
