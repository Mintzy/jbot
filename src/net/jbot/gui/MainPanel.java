package net.jbot.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import net.jbot.loader.RSApplet;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private BotConsole console;

	public MainPanel() {
		console = new BotConsole();
		setLayout(new BorderLayout());
		add(console.createScrollPane(), "South");
	}
	
	public void loadBot() {
		RSApplet applet = new RSApplet();
		try {
			add(applet.loadRS(), "Center");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BotConsole getConsole() {
		return console;
	}

}
