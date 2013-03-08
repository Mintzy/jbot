package net.jbot.analyzer.impl;

import java.util.ListIterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import net.jbot.analyzer.Analyzer;
import net.jbot.utils.AnalyzerUtil;

public class NodeAnalyzer extends Analyzer {

	@SuppressWarnings("unchecked")
	@Override
	protected boolean canRun(ClassNode node) {
		int own = 0, longs = 0;
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if ((fn.access & Opcodes.ACC_STATIC) == 0) {
				if (fn.desc.equals(String.format("L%s;", node.name)))
					own++;
				if (fn.desc.equals("J"))
					longs++;
			}
		}
		return own == 2 && longs == 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void analyze(ClassNode node) {
		AnalyzerUtil.addInterface(node, "net.jbot.analyzer.hooks.Node");
		String previousName = "";
		ListIterator<MethodNode> mnIt = node.methods.listIterator();
		methodIterator: while (mnIt.hasNext()) {
			MethodNode mn = mnIt.next();
			if ((mn.access & Opcodes.ACC_STATIC) == 0) {
				if (mn.desc.equals("()Z")) {
					ListIterator<AbstractInsnNode> ainIt = mn.instructions.iterator();
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
					AnalyzerUtil.addGetter(node, fn, "getUID");
				if (fn.name.equals(previousName))
					AnalyzerUtil.addGetter(node, fn, "getPrevious");
				if (fn.desc.equals(String.format("L%s;", node.name)) && !fn.name.equals(previousName))
					AnalyzerUtil.addGetter(node, fn, "getNext");
			}
		}
	}

}
