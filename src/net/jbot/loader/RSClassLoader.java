package net.jbot.loader;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.tree.ClassNode;

public class RSClassLoader extends ClassLoader {
	
	private Map<String, Object> hooks = new HashMap<String, Object>();
	
	public RSClassLoader() {
		
	}
	
	private void modify(ClassNode node) {
		
	}
	
}
