package net.jbot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
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
					cr.accept(cn, ClassReader.SKIP_DEBUG
							| ClassReader.SKIP_FRAMES);
					classes.put(cn.name, cn);
				}
			}
			jar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classes;
	}

	public static void dumpClasses(HashMap<String, ClassNode> classes) {
		try {
			File file = new File("out.jar");
			JarOutputStream out = new JarOutputStream(
					new FileOutputStream(file));
			for (ClassNode node : classes.values()) {
				JarEntry entry = new JarEntry(node.name + ".class");
				out.putNextEntry(entry);
				ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
				node.accept(writer);
				writer.toByteArray();
				byte[] b = writer.toByteArray();
				out.write(b);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
