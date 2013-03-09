package net.jbot.analyzer.impl;

import java.util.ListIterator;

import net.jbot.analyzer.Analyzer;
import net.jbot.utils.AnalyzerUtil;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class CanvasAnalyzer extends Analyzer{
	
	@Override
	protected boolean canRun(ClassNode node) {
		if (node.superName.equals("java/awt/Canvas"))
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void analyze(ClassNode node) {
		AnalyzerUtil.addInterface(node, "net.jbot.analyzer.hooks.Canvas");
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if (fn.desc.equals("Ljava/awt/Component;"))
				AnalyzerUtil.addGetter(node, fn, "getComponent");
		}
	}

}
