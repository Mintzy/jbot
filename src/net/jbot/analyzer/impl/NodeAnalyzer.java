package net.jbot.analyzer.impl;

import java.util.ListIterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

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

	@Override
	protected void analyze(ClassNode node) {
		AnalyzerUtil.addInterface(node, "net.jbot.analyzer.hooks.Node");
	}

}
