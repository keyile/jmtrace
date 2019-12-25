package mtrace;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

//this is the custom method visitor
class MethodAdapter extends MethodVisitor implements Opcodes {

    MethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        if(opcode== Opcodes.GETSTATIC) {
            mv.visitLdcInsn(Type.getType("L" + owner + ";"));
            mv.visitLdcInsn(name);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKESTATIC, "mtrace/Print", "traceStaticRead", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;)V", false);
        }
        else if(opcode == PUTSTATIC) {
            mv.visitLdcInsn(Type.getType("L" + owner + ";"));
            mv.visitLdcInsn(name);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKESTATIC, "mtrace/Print", "traceStaticWrite", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;)V", false);

        }
        else if(opcode == GETFIELD) {
            mv.visitInsn(DUP);
            mv.visitLdcInsn(name);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKESTATIC, "mtrace/Print", "traceFieldRead", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V", false);
        }
        else if(opcode == PUTFIELD) {
            mv.visitInsn(SWAP);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(name);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKESTATIC, "mtrace/Print", "traceFieldWrite", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V", false);
            mv.visitInsn(SWAP);
        }

        mv.visitFieldInsn(opcode, owner, name, descriptor);
    }
}
