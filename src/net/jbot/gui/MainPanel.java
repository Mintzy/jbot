package net.jbot.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;

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
		try {
			RSApplet applet = new RSApplet();
			add(applet.loadRS(), "Center");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("To load the RuneScape Applet, go to Bot>Load Bot", 250, 250);
    }

	public BotConsole getConsole() {
		return console;
	}

}
