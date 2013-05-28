package powercrystals.core.asm;

import cpw.mods.fml.relauncher.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class PCCASMTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
        if ("net.minecraft.world.WorldServer".equals(transformedName))
        {
            ClassReader cr = new ClassReader(bytes);
            ClassNode cn = new ClassNode(Opcodes.ASM4);
            cr.accept(cn, ClassReader.EXPAND_FRAMES);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            cn.accept(cw);
            /* new WorldServer constructor
             * WorldServer(MinecraftServer minecraftServer,
							ISaveHandler saveHandler, String worldName,
							WorldProvider provider, WorldSettings worldSettings,
							Profiler theProfiler, ILogAgent worldLogAgent)  
            **/
            cw.newMethod(name, "<init>", "(Lnet/minecraft/server/MinecraftServer;Lakf;Ljava/lang/String;Lacn;Laai;Lla;Lku;)V", true);
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(Lnet/minecraft/server/MinecraftServer;Lakf;Ljava/lang/String;Lacn;Laai;Lla;Lku;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitInsn(Opcodes.DUP);
            mv.visitInsn(Opcodes.DUP);
            mv.visitInsn(Opcodes.DUP);
            mv.visitInsn(Opcodes.DUP);
            mv.visitVarInsn(Opcodes.ALOAD, 2);
            mv.visitVarInsn(Opcodes.ALOAD, 3);
            mv.visitVarInsn(Opcodes.ALOAD, 4);
            mv.visitVarInsn(Opcodes.ALOAD, 5);
            mv.visitVarInsn(Opcodes.ALOAD, 6);
            mv.visitVarInsn(Opcodes.ALOAD, 7);
            // [World] super(saveHandler, par2String, provider, par4WorldSettings, theProfiler, worldLogAgent);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "aab", "<init>", "(Lakf;Ljava/lang/String;Lacn;Laai;Lla;Lku;)V");
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitFieldInsn(Opcodes.PUTFIELD, name, "a", "Lnet/minecraft/server/MinecraftServer;");
            mv.visitInsn(Opcodes.ACONST_NULL);
            mv.visitFieldInsn(Opcodes.PUTFIELD, name, "J", "Lit;");
            mv.visitInsn(Opcodes.ACONST_NULL);
            mv.visitFieldInsn(Opcodes.PUTFIELD, name, "K", "Liw;");
            mv.visitInsn(Opcodes.ACONST_NULL);
            mv.visitFieldInsn(Opcodes.PUTFIELD, name, "P", "Laao;");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(7, 10);
            mv.visitEnd();
            cw.visitEnd();
            return cw.toByteArray();
        }

        return bytes;
	}

}
