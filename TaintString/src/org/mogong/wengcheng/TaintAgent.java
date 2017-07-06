package org.mogong.wengcheng;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class TaintAgent {
	
	public static void premain(String args, Instrumentation inst){
		//inst.addTransformer(new PrintTransformer(), true);
		inst.addTransformer(new ClassFileTransformer() {
			public byte[] transform(ClassLoader l, String name, Class c,ProtectionDomain d, byte[] b)throws IllegalClassFormatException {
				ClassReader cr = new ClassReader(b);
				ClassWriter cw = new ClassWriter(cr, 0);
				ClassVisitor cv = new TaintAdapter.TaintClsAdapter(cw, name);
				cr.accept(cv, 0);
				return cw.toByteArray();
			}
		}, true);
		
		 Class[] classes = inst.getAllLoadedClasses();
		 
         for (Class c : classes) {
        	 if (inst.isModifiableClass(c) && c.getName().startsWith("java.lang.Runtime"))
        	 {
        		 try {
					inst.retransformClasses(c);
        		 } catch (UnmodifiableClassException e) {
					e.printStackTrace();
        		 }	 
        	  }
          }
	}
}
