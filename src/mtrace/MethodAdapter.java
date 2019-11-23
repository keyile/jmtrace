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
            mv.visitLdcInsn("GETSTATIC " + owner + " " + name + " " + descriptor);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        mv.visitFieldInsn(opcode, owner, name, descriptor);
    }
}
