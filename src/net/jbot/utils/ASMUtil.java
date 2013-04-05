package net.jbot.utils;

import java.util.ListIterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ASMUtil {

	@SuppressWarnings("unchecked")
	public static void addInterface(ClassNode node, String interfaceName) {
		node.interfaces.add(interfaceName);
		XMLUtil.addHook(node.name, interfaceName, interfaceName.substring(15));
		System.out.println("[^] " + node.name + " implements " + interfaceName);
	}

	@SuppressWarnings("unchecked")
	public static void addGetter(ClassNode node, FieldNode field, String method) {
		MethodNode mn = new MethodNode(Opcodes.ACC_PUBLIC, method, "()"
				+ field.desc + "." + field.name, null, null);
		mn.visitVarInsn(Opcodes.ALOAD, 0);
		mn.visitFieldInsn(Opcodes.GETFIELD, node.name, field.name, field.desc);
		mn.visitInsn(Type.getType(field.desc).getOpcode(Opcodes.IRETURN));
		mn.visitMaxs(0, 0);
		node.methods.add(mn);
		System.out.println("\t[*] " + method + "() gets field " + node.name
				+ "." + field.name);
	}

	public static void setSuper(ClassNode node, String superClass) {
		node.superName = superClass;
		ListIterator<?> mli = node.methods.listIterator();
		while (mli.hasNext()) {
			MethodNode mn = (MethodNode) mli.next();
			if (mn.name.equals("<init>")) {
				ListIterator<?> ili = mn.instructions.iterator();
				while (ili.hasNext()) {
					AbstractInsnNode ain = (AbstractInsnNode) ili.next();
					if (ain.getOpcode() == Opcodes.INVOKESPECIAL) {
						MethodInsnNode min = (MethodInsnNode) ain;
						min.owner = superClass;
						break;
					}
				}
			}
		}
		System.out.println("set " + node.name + " supername to "
				+ node.superName);
	}

}
