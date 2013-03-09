package net.jbot.analyzer.impl;

import java.util.ListIterator;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import net.jbot.analyzer.Analyzer;
import net.jbot.utils.AnalyzerUtil;

public class CharacterAnalyzer extends Analyzer {

	@SuppressWarnings("unchecked")
	@Override
	protected boolean canRun(ClassNode node) {
		int a = 0, b = 0;
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if (fn.desc.equals("I"))
				a++;
			if (fn.desc.equals("Ljava/lang/String;"))
				b++;
		}
		return a > 50 && b == 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void analyze(ClassNode node) {
		AnalyzerUtil.addInterface(node, "net.jbot.analyzer.hooks.Character");
		ListIterator<FieldNode> fnIt = node.fields.listIterator();
		while (fnIt.hasNext()) {
			FieldNode fn = fnIt.next();
			if (fn.desc.equals("Z"))
				AnalyzerUtil.addGetter(node, fn, "isWalking");
			if (fn.desc.equals("Ljava/lang/String;"))
				AnalyzerUtil.addGetter(node, fn, "getTextSpoken");
		}
	}

}
