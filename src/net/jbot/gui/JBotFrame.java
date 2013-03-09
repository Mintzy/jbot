package net.jbot.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.jbot.Constants;
import net.jbot.console.ConsoleOutputStream;
import net.jbot.utils.Util;
import net.jbot.utils.WebUtil;

@SuppressWarnings("serial")
public class JBotFrame extends JFrame implements ActionListener {

	private int width = 765, height = 623, logHeight = 120;

	private JBotMenuBar menuBar;
	private JBotToolBar toolBar;
	public JTextArea console;
	public JScrollPane scroll;

	public void buildFrame() {
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		setTitle(Constants.TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(getWidth(), getHeight()));
		setResizable(false);
		menuBar = new JBotMenuBar(this);
		toolBar = new JBotToolBar(this);
		buildConsole();
		add(toolBar, BorderLayout.NORTH);
		setJMenuBar(menuBar);
		pack();
		setVisible(true);
		Util.log("Welcome to " + Constants.TITLE);
	}

	private void buildConsole() {
		console = new JTextArea();
		scroll = new JScrollPane(console,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(getWidth(), getLogHeight()));
		console.setForeground(new Color(0x00, 0x80, 0x00));
		console.setBackground(Color.WHITE);
		console.setEditable(false);
		PrintStream ps = new PrintStream(new ConsoleOutputStream(console));
		System.setOut(ps);
		add(scroll, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equalsIgnoreCase("exit")) {
			int option = JOptionPane.showConfirmDialog(this,
					"Are you sure you want to exit " + Constants.NAME + "?",
					"Exit", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION)
				System.exit(0);
		}

		if (action.contains("Project thread"))
			WebUtil.openURL("http://www.rune-server.org/official-runescape/old-school-rs-2007/runescape-underground/475282-2007-open-source-bot-attempt.html");

		if (action.equalsIgnoreCase("about"))
			JOptionPane
					.showMessageDialog(
							this,
							Constants.TITLE
									+ " is made possible by the RuneScape client hacking communities such as MITB and JavaHacking.\nThis project was made to learn about RuneScape client hacking and the process of making a RuneScape bot.\nJBot was created as a solo project using many resources of said client hacking communities by Justin (Mr. Brightside, Mintzy).\nThank you for your interest in JBot and RuneScape client hacking.",
							"About", JOptionPane.INFORMATION_MESSAGE);

		if (action.contains("Github"))
			WebUtil.openURL("https://github.com/Mintzy/jbot");

		if (action.equalsIgnoreCase("screen shot")) {
			Util.screenShot(this);
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLogHeight() {
		return logHeight;
	}

}
