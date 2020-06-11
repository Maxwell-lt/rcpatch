package maxwell_lt.rcpatch;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.objectweb.asm.Opcodes.*;

public class RailcraftPatchClassTransformer implements IClassTransformer {

    private static final String TRANSFORM_CLASS = "mods.railcraft.common.util.inventory.InvTools";
    private static final Logger logger = Logger.getLogger("RCPatch");

    @Override
    public byte[] transform(String name, String transformedName, byte[] originalClass) {
        if (!transformedName.equals(TRANSFORM_CLASS)) {
            return originalClass;
        } else {
            logger.info(String.format("Transforming class %s", TRANSFORM_CLASS));
            try {
                ClassReader classReader = new ClassReader(originalClass);
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                RailcraftClassPatcher railcraftClassPatcher = new RailcraftClassPatcher(classWriter);

                classReader.accept(railcraftClassPatcher, 0);

                byte[] modifiedClass = classWriter.toByteArray();
                return classWriter.toByteArray();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to write class", e);
                return originalClass;
            }
        }
    }

    private static class RailcraftClassPatcher extends ClassVisitor {
        public RailcraftClassPatcher(ClassVisitor cv) {
            super(ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                                         String[] exceptions) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("getBlockStateFromStack") && desc.equals("(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/block/state/IBlockState;")) {
                logger.info("Modifying method getBlockStateFromStack");
                return new RailcraftMethodPatcher(mv);
            }
            return mv;
        }
    }

    private static class RailcraftMethodPatcher extends MethodVisitor {

        private final MethodVisitor method;

        public RailcraftMethodPatcher(MethodVisitor methodVisitor) {
            super(ASM5, null);
            this.method = methodVisitor;
        }
        @Override
        public void visitCode() {
            method.visitCode();
            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            method.visitTryCatchBlock(label0, label1, label2, "java/lang/IllegalArgumentException");
            Label label3 = new Label();
            method.visitLabel(label3);
            method.visitLineNumber(483, label3);
            method.visitVarInsn(ALOAD, 0);
            method.visitMethodInsn(INVOKESTATIC, "mods/railcraft/common/util/inventory/InvTools", "isEmpty", "(Lnet/minecraft/item/ItemStack;)Z", false);
            Label label4 = new Label();
            method.visitJumpInsn(IFEQ, label4);
            Label label5 = new Label();
            method.visitLabel(label5);
            method.visitLineNumber(484, label5);
            method.visitFieldInsn(GETSTATIC, "net/minecraft/init/Blocks", "field_150350_a", "Lnet/minecraft/block/Block;");
            method.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/block/Block", "func_176223_P", "()Lnet/minecraft/block/state/IBlockState;", false);
            method.visitInsn(ARETURN);
            method.visitLabel(label4);
            method.visitLineNumber(485, label4);
            method.visitFrame(F_SAME, 0, null, 0, null);
            method.visitVarInsn(ALOAD, 0);
            method.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/item/ItemStack", "func_77973_b", "()Lnet/minecraft/item/Item;", false);
            method.visitVarInsn(ASTORE, 1);
            Label label6 = new Label();
            method.visitLabel(label6);
            method.visitLineNumber(486, label6);
            method.visitMethodInsn(INVOKESTATIC, "net/minecraftforge/registries/GameData", "getBlockItemMap", "()Lcom/google/common/collect/BiMap;", false);
            method.visitMethodInsn(INVOKEINTERFACE, "com/google/common/collect/BiMap", "inverse", "()Lcom/google/common/collect/BiMap;", true);
            method.visitVarInsn(ALOAD, 1);
            method.visitMethodInsn(INVOKEINTERFACE, "com/google/common/collect/BiMap", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
            method.visitTypeInsn(CHECKCAST, "net/minecraft/block/Block");
            method.visitVarInsn(ASTORE, 2);
            method.visitLabel(label0);
            method.visitLineNumber(489, label0);
            method.visitVarInsn(ALOAD, 2);
            Label label7 = new Label();
            method.visitJumpInsn(IFNONNULL, label7);
            method.visitFieldInsn(GETSTATIC, "net/minecraft/init/Blocks", "field_150350_a", "Lnet/minecraft/block/Block;");
            method.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/block/Block", "func_176223_P", "()Lnet/minecraft/block/state/IBlockState;", false);
            method.visitJumpInsn(GOTO, label1);
            method.visitLabel(label7);
            method.visitFrame(F_APPEND, 2, new Object[]{"net/minecraft/item/Item", "net/minecraft/block/Block"}, 0, null);
            method.visitVarInsn(ALOAD, 2);
            method.visitVarInsn(ALOAD, 0);
            method.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/item/ItemStack", "func_77952_i", "()I", false);
            method.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/block/Block", "func_176203_a", "(I)Lnet/minecraft/block/state/IBlockState;", false);
            method.visitLabel(label1);
            method.visitFrame(F_SAME1, 0, null, 1, new Object[]{"net/minecraft/block/state/IBlockState"});
            method.visitInsn(ARETURN);
            method.visitLabel(label2);
            method.visitLineNumber(490, label2);
            method.visitFrame(F_SAME1, 0, null, 1, new Object[]{"java/lang/IllegalArgumentException"});
            method.visitVarInsn(ASTORE, 3);
            Label label8 = new Label();
            method.visitLabel(label8);
            method.visitLineNumber(491, label8);
            method.visitFieldInsn(GETSTATIC, "net/minecraft/init/Blocks", "field_150350_a", "Lnet/minecraft/block/Block;");
            method.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/block/Block", "func_176223_P", "()Lnet/minecraft/block/state/IBlockState;", false);
            method.visitInsn(ARETURN);
            Label label9 = new Label();
            method.visitLabel(label9);
            method.visitLocalVariable("e", "Ljava/lang/IllegalArgumentException;", null, label8, label9, 3);
            method.visitLocalVariable("stack", "Lnet/minecraft/item/ItemStack;", null, label3, label9, 0);
            method.visitLocalVariable("item", "Lnet/minecraft/item/Item;", null, label6, label9, 1);
            method.visitLocalVariable("block", "Lnet/minecraft/block/Block;", null, label0, label9, 2);
            method.visitMaxs(2, 4);
            method.visitEnd();
        }

    }
}
