package net.jbot.analyzer.impl;

import org.objectweb.asm.tree.ClassNode;

import net.jbot.analyzer.Analyzer;
import net.jbot.utils.AnalyzerUtil;

public class ClientAnalyzer extends Analyzer {

	@Override
	protected boolean canRun(ClassNode node) {
		if (node.name.equals("client"))
			return true;
		return false;
	}

	@Override
	protected void analyze(ClassNode node) {
		AnalyzerUtil.addInterface(node, "net.jbot.analyzer.hooks.Client");
	}

}
