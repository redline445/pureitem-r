package redline.coreMod;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import net.minecraft.launchwrapper.IClassTransformer;

import javax.crypto.Mac;
import java.util.Iterator;
import static org.objectweb.asm.Opcodes.*;
public class ClassTransformer implements IClassTransformer {
    public ClassTransformer() {
    }

    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        AbstractInsnNode[] var7;
        int var8;
        int var9;
        AbstractInsnNode node;
        ClassNode craft;
        Iterator var12;
        ClassReader classReader;
        ClassWriter classWriter;
        ClassVisitor classVisitor;
        if (name.equals("net.minecraftforge.common.ForgeHooks")) {
            classReader = new ClassReader(basicClass);
            classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            classVisitor = new ClassVisitor(ASM4, classWriter) {
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                    System.out.println("fuck Hook");
                    MethodVisitor mv;
                    Label start;
                    Label end;
                    if (name.equals("onLivingUpdate")) {
                        System.out.println("fuck update");
                        mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
                        mv.visitCode();
                        start = new Label();
                        mv.visitLabel(start);
                        mv.visitVarInsn(ALOAD, 0);
                        mv.visitMethodInsn(INVOKESTATIC, "redline/coreMod/write", "onLivingUpdate", "(Lnet/minecraft/entity/EntityLivingBase;)Z", false);
                        mv.visitInsn(IRETURN);
                        end = new Label();
                        mv.visitLabel(end);
                        mv.visitLocalVariable("entity", "Lnet/minecraft/entity/EntityLivingBase;", (String) null, start, end, 0);
                        mv.visitMaxs(1, 1);
                        mv.visitEnd();
                        return null;
                    } else {
                        return this.cv.visitMethod(access, name, desc, signature, exceptions);
                    }
                }
            };
            classReader.accept(classVisitor, ASM4);
            return classWriter.toByteArray();
        } else if (!name.equals("sa") && !name.equals("net.minecraft.entity.Entity")) {
            if (!name.equals("ahb") && !name.equals("net.minecraft.world.World")) {
                if (!name.equals("sv") && !name.equals("net.minecraft.entity.EntityLivingBase")) {
                    if (!name.equals("bao") && !name.equals("net.minecraft.client.Minecraft")) {
                        return basicClass;
                    } else {
                        System.out.println("FFFFFFFFF");
                        classReader = new ClassReader(basicClass);
                        classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                        classVisitor = new ClassVisitor(ASM4, classWriter) {
                            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                                if (name.equals("T") || name.equals("aw") || name.equals("Q") || name.equals("X") || name.equals("ae") || name.equals("E") || name.equals("au") || name.equals("at") || name.equals("D") || name.equals("C") || name.equals("R")) {
                                    access = 1;
                                }
                                return this.cv.visitField(access, name, desc, signature, value);
                            }

                            public MethodVisitor visitMethod(int accessx, String name, String desc, String signature, String[] exceptions) {
                                if ((!name.equals("p") || !desc.equals("()V")) && !name.equals("runTick")) {
                                    if ((!name.equals("ak") || !desc.equals("()V")) && !name.equals("runGameLoop")) {
                                        return this.cv.visitMethod(accessx, name, desc, signature, exceptions);
                                    } else {
                                        System.out.println("RRRRRRRRR");
                                        int access = 1;
                                        return new MethodVisitor(ASM4, this.cv.visitMethod(access, name, desc, signature, exceptions)) {
                                            public void visitCode() {
                                                this.mv.visitVarInsn(ALOAD, 0);
                                                this.mv.visitMethodInsn(INVOKESTATIC, "redline/coreMod/write", "runGameLoop", "(Lnet/minecraft/client/Minecraft;)V", false);
                                            }
                                        };
                                    }
                                } else {
                                    System.out.println("MMMMMMMMMMM");
                                    return new MethodVisitor(ASM4, this.cv.visitMethod(accessx, name, desc, signature, exceptions)) {
                                        public void visitCode() {
                                            this.mv.visitVarInsn(ALOAD, 0);
                                            this.mv.visitMethodInsn(INVOKESTATIC, "redline/coreMod/write", "runTick", "(Lnet/minecraft/client/Minecraft;)V", false);
                                        }
                                    };
                                }
                            }
                        };
                        classReader.accept(classVisitor, ASM4);
                        return classWriter.toByteArray();
                    }
                } else {
                    System.out.println("fuck EntityLivingBase");
                    classReader = new ClassReader(basicClass);
                    classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                    classVisitor = new ClassVisitor(ASM4, classWriter) {
                        @Override
                        public void visit(int version, int access, String name, String signature, String superName, String[]
                                interfaces) {
                            super.visit(version, access, name, signature, superName, interfaces);
                            FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC, "setTrueDead", "Z", null, null);
                            fv.visitEnd();
                            fv = cv.visitField(Opcodes.ACC_PUBLIC, "setKIll", "Z", null, null);
                            fv.visitEnd();
                            fv = cv.visitField(Opcodes.ACC_PUBLIC, "onProtect", "Z", null, null);
                            fv.visitEnd();
                            MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, coreLoader.debug ? "getHealth" : "aS", "()F", null, null);
                            mv.visitCode();
                            Label start = new Label();
                            mv.visitLabel(start);
                            mv.visitVarInsn(Opcodes.ALOAD, 0);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "redline/coreMod/write", "getHealth", "(Lnet/minecraft/entity/EntityLivingBase;)F", false);
                            mv.visitInsn(Opcodes.FRETURN);
                            Label end = new Label();
                            mv.visitLabel(end);
                            mv.visitLocalVariable("this", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
                            mv.visitMaxs(1, 1);
                            mv.visitEnd();
                            mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, coreLoader.debug ? "getMaxHealth" : "aY", "()F", null, null);
                            mv.visitCode();
                            start = new Label();
                            mv.visitLabel(start);
                            mv.visitVarInsn(Opcodes.ALOAD, 0);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "redline/coreMod/write", "getMaxHealth", "(Lnet/minecraft/entity/EntityLivingBase;)F", false);
                            mv.visitInsn(Opcodes.FRETURN);
                            end = new Label();
                            mv.visitLabel(end);
                            mv.visitLocalVariable("this", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
                            mv.visitMaxs(1, 1);
                            mv.visitEnd();
                        }

                        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                            if (((name.equals("aS")) && (desc.equals("()F"))) || (name.equals("getHealth"))) {
                                return cv.visitMethod(access, "getHealth2", desc, signature, exceptions);
                            } else if (((name.equals("aY")) && (desc.equals("()F"))) || (name.equals("getMaxHealth"))) {
                                return cv.visitMethod(access, "getMaxHealth2", desc, signature, exceptions);
                            } else if (name.equals("getHealth2") || name.equals("getMaxHealth2")) {
                                return null;
                            }
                            return this.cv.visitMethod(access, name, desc, signature, exceptions);
                        }
                    };
                    classReader.accept(classVisitor, ASM4);
                    return classWriter.toByteArray();
                }
            } else {
                classReader = new ClassReader(basicClass);
                classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                classVisitor = new ClassVisitor(ASM4, classWriter) {
                    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                        if (name.equals("worldAccesses") || name.equals("u")) {
                            access = ACC_PUBLIC;
                        }
                        if (name.equals("unloadedEntityList") || name.equals("f")) {
                            access = ACC_PUBLIC;
                        }
                        return this.cv.visitField(access, name, desc, signature, value);
                    }

                    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                        MethodVisitor mv;
                        Label start;
                        Label end;
                        if (!name.equals("updateEntity") && (!name.equals("g") || !desc.equals("(Lsa;)V"))) {
                            if (!name.equals("removeEntity") && (!name.equals("e") || !desc.equals("(Lsa;)V"))) {
                                if ((!name.equals("f") || !desc.equals("(Lsa;)V")) && !name.equals("removePlayerEntityDangerously")) {
                                    if (((!name.equals("b") || !desc.equals("(Lsa;)V")) && !name.equals("onEntityRemoved"))) {
                                        return this.cv.visitMethod(access, name, desc, signature, exceptions);
                                    } else {
                                        System.out.println("fuck on");
                                        mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
                                        mv.visitCode();
                                        start = new Label();
                                        mv.visitLabel(start);
                                        mv.visitVarInsn(ALOAD, 0);
                                        mv.visitVarInsn(ALOAD, 1);
                                        mv.visitMethodInsn(INVOKESTATIC, "redline/coreMod/write", "onEntityRemoved", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false);
                                        mv.visitInsn(RETURN);
                                        end = new Label();
                                        mv.visitLabel(end);
                                        mv.visitLocalVariable("this", "Lnet/minecraft/world/World;", (String) null, start, end, 0);
                                        mv.visitLocalVariable("p_72847_1_", "Lnet/minecraft/entity/Entity;", (String) null, start, end, 1);
                                        mv.visitMaxs(2, 2);
                                        mv.visitEnd();
                                        return null;
                                    }
                                } else {
                                    System.out.println("fuck removeDanger");
                                    mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
                                    mv.visitCode();
                                    start = new Label();
                                    mv.visitLabel(start);
                                    mv.visitVarInsn(ALOAD, 0);
                                    mv.visitVarInsn(ALOAD, 1);
                                    mv.visitMethodInsn(INVOKESTATIC, "redline/coreMod/write", "removePlayerEntityDangerously", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false);
                                    mv.visitInsn(RETURN);
                                    end = new Label();
                                    mv.visitLabel(end);
                                    mv.visitLocalVariable("this", "Lnet/minecraft/world/World;", (String) null, start, end, 0);
                                    mv.visitLocalVariable("p_72973_1", "Lnet/minecraft/entity/Entity;", (String) null, start, end, 1);
                                    mv.visitMaxs(2, 2);
                                    mv.visitEnd();
                                    return null;
                                }
                            } else {
                                System.out.println("fuck removeEntity");
                                mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
                                mv.visitCode();
                                start = new Label();
                                mv.visitLabel(start);
                                mv.visitVarInsn(ALOAD, 0);
                                mv.visitVarInsn(ALOAD, 1);
                                mv.visitMethodInsn(INVOKESTATIC, "redline/coreMod/write", "removeEntity", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false);
                                mv.visitInsn(RETURN);
                                end = new Label();
                                mv.visitLabel(end);
                                mv.visitLocalVariable("this", "Lnet/minecraft/world/World;", (String) null, start, end, 0);
                                mv.visitLocalVariable("p_72900_1_", "Lnet/minecraft/entity/Entity;", (String) null, start, end, 1);
                                mv.visitMaxs(2, 2);
                                mv.visitEnd();
                                return null;
                            }
                        } else {
                            System.out.println("fuck update update");
                            mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
                            mv.visitCode();
                            start = new Label();
                            mv.visitLabel(start);
                            mv.visitVarInsn(ALOAD, 0);
                            mv.visitVarInsn(ALOAD, 1);
                            mv.visitMethodInsn(INVOKESTATIC, "redline/coreMod/write", "updateEntity", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false);
                            mv.visitInsn(RETURN);
                            end = new Label();
                            mv.visitLabel(end);
                            mv.visitLocalVariable("this", "Lnet/minecraft/world/World;", (String) null, start, end, 0);
                            mv.visitLocalVariable("p_72870_1_", "Lnet/minecraft/entity/Entity;", (String) null, start, end, 1);
                            mv.visitMaxs(2, 2);
                            mv.visitEnd();
                            return null;
                        }
                    }
                };
                classReader.accept(classVisitor, ASM4);
                return classWriter.toByteArray();
            }
        } else {
            System.out.println("fuck Entity");
            classReader = new ClassReader(basicClass);
            classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            classVisitor = new ClassVisitor(ASM4, classWriter) {
                @Override
                public void visit(int version, int access, String name, String signature, String superName, String[]
                        interfaces) {
                    super.visit(version, access, name, signature, superName, interfaces);
                    FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC, "setUnUpdate", "Z", null, null);
                    fv.visitEnd();
                    fv = cv.visitField(Opcodes.ACC_PUBLIC, "spawnWC", "Z", null, null);
                    fv.visitEnd();
                }
            };
        }
        classReader.accept(classVisitor, ASM4);
        return classWriter.toByteArray();
    }
}

