package net.jbot.updater.analyzer.impl;

import net.jbot.updater.analyzer.AbstractAnalyzer;
import net.jbot.utils.ASMUtil;

import org.objectweb.asm.tree.ClassNode;

public class CanvasAnalyzer extends AbstractAnalyzer {

	@Override
	protected boolean canRun(ClassNode node) {
		if (node.superName.contains("Canvas")) {
			return true;
		}
		return false;
	}

	@Override
	protected void analyze(ClassNode node) {
		ASMUtil.setSuper(node, "net/jbot/graphics/RSCanvas");
	}

}
