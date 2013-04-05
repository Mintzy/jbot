package net.jbot.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class JarUtil {
	
	public static HashMap<String, ClassNode> parseJar(JarFile jar) {
		HashMap<String, ClassNode> classes = new HashMap<>();
		try {
			Enumeration<?> enumeration = jar.entries();
			while (enumeration.hasMoreElements()) {
				JarEntry entry = (JarEntry) enumeration.nextElement();
				if (entry.getName().endsWith(".class")) {
					ClassReader cr = new ClassReader(jar.getInputStream(entry));
					ClassNode cn = new ClassNode();
					cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
					classes.put(cn.name, cn);
				}
			}
			jar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classes;
	}

}
