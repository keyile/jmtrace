package mtrace;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

//this is the custom method visitor
class MethodAdapter extends MethodVisitor implements Opcodes {

    MethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        if(opcode== Opcodes.GETSTATIC) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("GETSTATIC " + owner.replace("/", ".") + "." + name + " : " + descriptor);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        else if(opcode == PUTSTATIC) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("PUTSTATIC " + owner.replace("/", ".") + "." + name + " : " + descriptor);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        else if(opcode == GETFIELD) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("GETFIELD " + owner.replace("/", ".") + "." + name + " : " + descriptor);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        else if(opcode == PUTFIELD) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("PUTFIELD " + owner.replace("/", ".") + "." + name + " : " + descriptor);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }

        mv.visitFieldInsn(opcode, owner, name, descriptor);
    }
}
