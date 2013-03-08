package net.jbot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class JarUtil {
	
	public static ArrayList<ClassNode> parseJar(JarFile jar) {
		ArrayList<ClassNode> classes = new ArrayList<ClassNode>();
		try {
			Enumeration<?> enumeration = jar.entries();
			while (enumeration.hasMoreElements()) {
				JarEntry entry = (JarEntry) enumeration.nextElement();
				if (entry.getName().endsWith(".class")) {
					ClassReader cr = new ClassReader(jar.getInputStream(entry));
					ClassNode cn = new ClassNode();
					cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
					classes.add(cn);
				}
			}
			jar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classes;
	}
	
	public static void dumpClasses(File jar, ClassNode[] classes) {
		try {
			JarOutputStream out = new JarOutputStream(new FileOutputStream(jar));
			for (ClassNode node : classes) {
				JarEntry entry = new JarEntry(node.name + ".class");
				out.putNextEntry(entry);
				ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
				node.accept(writer);
				out.write(writer.toByteArray());
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
