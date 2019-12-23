package mtrace;

import org.objectweb.asm.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Instrumenter {
    public static void main(final String[] args) throws Exception {
        FileInputStream is = new FileInputStream("Test.class");

        ClassReader cr = new ClassReader(is);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassAdapter ct = new ClassAdapter(cw);

        cr.accept(ct, 0);

        FileOutputStream fos = new FileOutputStream("Test.class");
        fos.write(cw.toByteArray());
        fos.close();
    }
}

