package net.jbot.updater.analyzer.impl;

import java.util.ListIterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import net.jbot.updater.analyzer.AbstractAnalyzer;
import net.jbot.utils.ASMUtil;

public class PlayerAnalyzer extends AbstractAnalyzer {

	@SuppressWarnings("unchecked")
	@Override
	protected boolean canRun(ClassNode node) {
		if (node.superName.isEmpty()) {
			return false;
		}
		int string = 0, model = 0;
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if ((fn.access & Opcodes.ACC_STATIC) == 0) {
				if (fn.desc.equals("Ljava/lang/String;")) {
					string++;
				}
				if (fn.desc.equals("Ldk;")) {
					model++;
				}
			}
		}
		return string == 1 && model == 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void analyze(ClassNode node) {
		ASMUtil.addInterface(node, "net/jbot/hooks/PlayerHook");
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if ((fn.access & Opcodes.ACC_STATIC) == 0) {
				if (fn.desc.equals("Ljava/lang/String;")) {
					ASMUtil.addGetter(node, fn, "getUsername");
				}
			}
		}
	}

}
