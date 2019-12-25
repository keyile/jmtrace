package mtrace;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassInstrumenter implements ClassFileTransformer {
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

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // Return null if no transform is performed.
        if (className.startsWith("java") || className.startsWith("sun") || className.startsWith("jdk")
        || className.startsWith("mtrace")) {
            return null;
        }

        System.out.println("Transforming: " + className);

        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassAdapter ct = new ClassAdapter(cw);

        cr.accept(ct, 0);

        return cw.toByteArray();
    }

}

