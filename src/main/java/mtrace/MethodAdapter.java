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

    @Override
    public void visitInsn(int opcode) {
        if(opcode == IALOAD) {
            mv.visitInsn(DUP2);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKESTATIC, "mtrace/Print", "traceArrayRead", "(Ljava/lang/Object;ILjava/lang/Object;)V", false);
        } else if(opcode == IASTORE) {
            // v1, v2, v3
            mv.visitInsn(DUP_X2);
            mv.visitInsn(POP);
            // v3, v1, v2
            mv.visitInsn(DUP2);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKESTATIC, "mtrace/Print", "traceArrayWrite", "(Ljava/lang/Object;ILjava/lang/Object;)V", false);
            // v3, v1, v2
            mv.visitInsn(DUP2_X1);
            mv.visitInsn(POP2);
        }

        mv.visitInsn(opcode);
    }
}
