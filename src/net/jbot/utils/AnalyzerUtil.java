package net.jbot.utils;

import org.objectweb.asm.tree.ClassNode;

public class AnalyzerUtil {
	
	@SuppressWarnings("unchecked")
	public static void addInterface(ClassNode node, String interfaceName) {
		node.interfaces.add(interfaceName);
		System.out.println("[^] " + node.name + " implements " + interfaceName);
	}

}
