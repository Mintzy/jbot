package net.jbot.updater;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarFile;

import net.jbot.updater.analyzer.AbstractAnalyzer;
import net.jbot.updater.analyzer.impl.CanvasAnalyzer;
import net.jbot.updater.analyzer.impl.NodeAnalyzer;
import net.jbot.utils.JarUtil;
import net.jbot.utils.XMLUtil;

import org.objectweb.asm.tree.ClassNode;

public class Updater {

	public static HashMap<String, ClassNode> classes = new HashMap<String, ClassNode>();
	private ArrayList<AbstractAnalyzer> analyzers = new ArrayList<AbstractAnalyzer>();
	
	private long startTime, endTime;
	
	public static void main(String[] args) {
		System.out.println("Starting JBot Updater..");
		new Updater();
	}

	public Updater() {
		startTime = System.currentTimeMillis();
		JarFile jar;
		File xml;
		try {
			xml = new File("hooks.xml");
			if (xml.exists()) {
				xml.delete();
				XMLUtil.buildSkeleton();
			}
			jar = new JarFile("gamepack.jar");
			classes = JarUtil.parseJar(jar);
			System.out.println(classes.values().size() + " classes parsed.");
			this.loadAnalyzers();
			this.runAnalyzers();
		} catch (IOException e) {
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Finished in " + endTime + "ms.");
	}
	
	private void loadAnalyzers() {
		this.analyzers.add(new CanvasAnalyzer());
		this.analyzers.add(new NodeAnalyzer());
	}
	
	private void runAnalyzers() {
		for (ClassNode node : classes.values()) {
			for (AbstractAnalyzer analyzer : this.analyzers) {
				analyzer.run(node);
			}
		}
	}

}
