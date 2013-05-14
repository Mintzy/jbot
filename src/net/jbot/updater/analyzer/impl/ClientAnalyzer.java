package net.jbot.updater.analyzer.impl;

import java.util.ListIterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import net.jbot.updater.analyzer.AbstractAnalyzer;
import net.jbot.utils.ASMUtil;

public class ClientAnalyzer extends AbstractAnalyzer {

	@Override
	protected boolean canRun(ClassNode node) {
		if (node.name.equals("client")) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void analyze(ClassNode node) {
		ASMUtil.addInterface(node, "net/jbot/hooks/ClientHook");
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if ((fn.access & Opcodes.ACC_STATIC) == 1) {
				if (fn.desc.equals("Lcz;")) {
					//ASMUtil.addGetter(node, fn, "getLocalPlayer"); <-- must find method and then field hurrdurr
				}
			}
		}
	}


}
