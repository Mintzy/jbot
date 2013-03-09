package net.jbot.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

import net.jbot.analyzer.impl.CanvasAnalyzer;
import net.jbot.analyzer.impl.CharacterAnalyzer;
import net.jbot.analyzer.impl.ClientAnalyzer;
import net.jbot.analyzer.impl.NodeAnalyzer;
import net.jbot.utils.JarUtil;
import net.jbot.utils.WebUtil;

import org.objectweb.asm.tree.ClassNode;

public class Updater {

	private ArrayList<ClassNode> classes = new ArrayList<ClassNode>();
	// public static Map<String, ClassNode> classes = new HashMap<String,
	// ClassNode>();
	private ArrayList<Analyzer> analyzers = new ArrayList<Analyzer>();

	public static void main(String[] args) {
		new Updater();
	}

	public Updater() {
		try {
			System.out.println("Starting JBot Updater..");
			// First, lets read the page and obtain the source
			String source = WebUtil
					.readPage("http://oldschool20.runescape.com");
			// Next, lets find the gamepack from the source
			String gamepack = WebUtil.findGamepack(source);
			String gamepackURL = "http://oldschool20.runescape.com/" + gamepack;
			// Now, lets download the gamepack archive
			WebUtil.downloadGamepack(gamepackURL);
			// Next, we have to parse the jar. Meaning, find all the classes
			// within the jar.
			File gp = new File("runescape.jar");
			JarFile jar = new JarFile("runescape.jar");
			classes = JarUtil.parseJar(jar);
			// Lets load & run the analyzers and identify those
			// classes/methods/fields
			loadAnalyzers();
			runAnalyzers();
			// Finally, dump all the classes back into the jar
			JarUtil.dumpClasses(gp, classes.toArray((new ClassNode[0])));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadAnalyzers() {
		this.analyzers.add(new NodeAnalyzer());
		this.analyzers.add(new ClientAnalyzer());
		this.analyzers.add(new CharacterAnalyzer());
		this.analyzers.add(new CanvasAnalyzer());
	}

	private void runAnalyzers() {
		for (ClassNode node : classes) {
			for (Analyzer analyzer : analyzers) {
				analyzer.run(node);
			}
		}
	}

}
