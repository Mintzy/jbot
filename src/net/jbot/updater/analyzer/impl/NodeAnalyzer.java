package net.jbot.updater.analyzer.impl;

import java.util.ListIterator;

import net.jbot.updater.analyzer.AbstractAnalyzer;
import net.jbot.utils.ASMUtil;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class NodeAnalyzer extends AbstractAnalyzer {

	@SuppressWarnings("unchecked")
	@Override
	protected boolean canRun(ClassNode node) {
		int ownType = 0, longType = 0;
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if ((fn.access & Opcodes.ACC_STATIC) == 0) {
				if (fn.desc.equals(String.format("L%s;", node.name)))
					ownType++;
				if (fn.desc.equals("J")) {
					longType++;
				}
			}
		}
		return ownType == 2 && longType == 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void analyze(ClassNode node) {
		ASMUtil.addInterface(node, "net.jbot.hooks.Node");
		String previousName = "";
		ListIterator<MethodNode> mnIt = node.methods.listIterator();
		methodIterator: while (mnIt.hasNext()) {
			MethodNode mn = mnIt.next();
			if ((mn.access & Opcodes.ACC_STATIC) == 0) {
				if (mn.desc.equals("()Z")) {
					ListIterator<AbstractInsnNode> ainIt = mn.instructions
							.iterator();
					while (ainIt.hasNext()) {
						AbstractInsnNode ain = ainIt.next();
						if (ain instanceof FieldInsnNode) {
							previousName = ((FieldInsnNode) ain).name;
							break methodIterator;
						}
					}
				}
			}
		}
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if ((fn.access & Opcodes.ACC_STATIC) == 0) {
				if (fn.desc.equals("J"))
					ASMUtil.addGetter(node, fn, "getUID");
				if (fn.name.equals(previousName))
					ASMUtil.addGetter(node, fn, "getPrevious");
				if (fn.desc.equals(String.format("L%s;", node.name))
						&& !fn.name.equals(previousName))
					ASMUtil.addGetter(node, fn, "getNext");
			}
		}
	}

}
