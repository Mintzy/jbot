package net.jbot.utils;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

public class AnalyzerUtil {
	
	@SuppressWarnings("unchecked")
	public static void addInterface(ClassNode node, String interfaceName) {
		node.interfaces.add(interfaceName);
		System.out.println("[^] " + node.name + " implements " + interfaceName);
	}
	
	@SuppressWarnings("unchecked")
	public static void addGetter(ClassNode node, FieldNode field, String method) {
		MethodNode mn = new MethodNode(Opcodes.ACC_PUBLIC, method, "()" + field.desc + "." + field.name, null, null);
		mn.visitVarInsn(Opcodes.ALOAD, 0);
		mn.visitFieldInsn(Opcodes.GETFIELD, node.name, field.name, field.desc);
		mn.visitInsn(Type.getType(field.desc).getOpcode(Opcodes.IRETURN));
		mn.visitMaxs(0, 0);
		node.methods.add(mn);
		System.out.println("\t[*] " + method + "() gets field " + node.name + "." + field.name);
	}

}
