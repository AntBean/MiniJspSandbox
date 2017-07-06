package org.mogong.wengcheng;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;

public class TaintAdapter {
	
	
	public static class TaintClsAdapter extends ClassVisitor { 
		
		private String classname;
		
		public TaintClsAdapter(ClassVisitor cv, String name) 
		{ 
			super(Opcodes.ASM5, cv); 
			classname = name;
		} 
		
		@Override 
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) 
		{
			MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions); 
			
			//System.out.println( this.classname + " " + name + " " + desc);
			
			if (this.classname.equals("java/lang/Runtime") && name.equals("exec") && desc.startsWith("(Ljava/lang/String;)Ljava/lang/Process")) 
				return new MethodAdapter(mv); 
			else 
				return mv; 
		}
	
	}
	
	public static class MethodAdapter extends MethodVisitor { 
		
		public MethodAdapter(MethodVisitor mv) 
		{ 
			super(Opcodes.ASM5, mv); 
			
			this.mv = mv; 
		} 
		
		@Override
		public void visitCode(){
			
			//System.out.println("in visiting code");
			
			mv.visitCode();
			
			Label start = new Label();
			
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "getTaint", "()Z");
			mv.visitJumpInsn(Opcodes.IFEQ, start);
			
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("find java runtime trojan");
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
			
			mv.visitLabel(start);
		}
		
		@Override
		public void visitMaxs(int maxStack, int maxLocals){
			mv.visitMaxs(maxStack + 4, maxLocals);
		}
	}

}
