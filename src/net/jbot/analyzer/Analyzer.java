package net.jbot.analyzer;

import org.objectweb.asm.tree.ClassNode;

public abstract class Analyzer {
	
	public void run(ClassNode node) {
		if (this.canRun(node)) {
			this.analyze(node);
		}
	}
	
	protected abstract boolean canRun(ClassNode node);
	protected abstract void analyze(ClassNode node);

}
