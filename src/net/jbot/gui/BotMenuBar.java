package net.jbot.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class BotMenuBar extends JMenuBar {

	private static final long serialVersionUID = 4984650483752432859L;

	private ActionListener listener;

	public static final String[] TITLES;
	public static final String[][] ELEMENTS;

	static {
		TITLES = new String[] { "File", "Bot" };
		ELEMENTS = new String[][] { { "About", "-", "Exit" }, { "Load Bot" } };
	}

	public BotMenuBar(ActionListener listener) {
		this.listener = listener;
		for (int i = 0; i < TITLES.length; i++) {
			String titles = TITLES[i];
			String[] elements = ELEMENTS[i];
			add(buildMenu(titles, elements));
		}
	}

	private JMenu buildMenu(String titles, String[] elements) {
		JMenu menu = new JMenu(titles);
		for (String e : elements) {
			if (e.equals("-")) {
				menu.add(new JSeparator());
			} else {
				JMenuItem jmi = new JMenuItem(e);
				jmi.addActionListener(listener);
				menu.add(jmi);
			}
		}
		return menu;
	}


}
