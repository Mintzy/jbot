package net.jbot.updater.analyzer;

import org.objectweb.asm.tree.ClassNode;

public abstract class AbstractAnalyzer {
	
	public void run(ClassNode node) {
		if (this.canRun(node)) {
			this.analyze(node);
		}
	}
	
	protected abstract boolean canRun(ClassNode node);
	protected abstract void analyze(ClassNode node);

}
