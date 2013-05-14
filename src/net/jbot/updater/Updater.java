package net.jbot.updater;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarFile;

import net.jbot.updater.analyzer.AbstractAnalyzer;
import net.jbot.updater.analyzer.impl.CanvasAnalyzer;
import net.jbot.updater.analyzer.impl.ClientAnalyzer;
import net.jbot.updater.analyzer.impl.NodeAnalyzer;
import net.jbot.updater.analyzer.impl.PlayerAnalyzer;
import net.jbot.utils.JarUtil;

import org.objectweb.asm.tree.ClassNode;

public class Updater {

	public static HashMap<String, ClassNode> classes = new HashMap<String, ClassNode>();
	private ArrayList<AbstractAnalyzer> analyzers = new ArrayList<AbstractAnalyzer>();

	public boolean dump = true;

	private long startTime, endTime;

	public static void main(String[] args) {
		System.out.println("Starting JBot Updater..");
		//System.out.println("RS Revision: " + getCurrentRevision());
		new Updater();
	}

	public Updater() {
		startTime = System.currentTimeMillis();
		JarFile jar;
		try {
			jar = new JarFile("gamepack.jar");
			classes = JarUtil.parseJar(jar);
			System.out.println(classes.values().size() + " classes parsed.");
			this.loadAnalyzers();
			this.runAnalyzers();
			if (dump) {
				JarUtil.dumpClasses(classes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Finished in " + endTime + "ms.");
	}

	private void loadAnalyzers() {
		this.analyzers.add(new CanvasAnalyzer());
		this.analyzers.add(new NodeAnalyzer());
		this.analyzers.add(new ClientAnalyzer());
		this.analyzers.add(new PlayerAnalyzer());
	}

	private void runAnalyzers() {
		for (ClassNode node : classes.values()) {
			for (AbstractAnalyzer analyzer : this.analyzers) {
				analyzer.run(node);
			}
		}
	}

	private static int getCurrentRevision() {
		int rev = 10;
		try {
			while (rev < 30) {
				Socket s = new Socket("oldschool1.runescape.com", 43594);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				DataInputStream dis = new DataInputStream(s.getInputStream());
				dos.writeByte(15);
				dos.writeInt(rev);
				dos.flush();
				byte[] recv = new byte[1];
				dis.read(recv);
				s.close();
				if (recv[0] == 0) {
					return rev;
				} else {
					rev++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
