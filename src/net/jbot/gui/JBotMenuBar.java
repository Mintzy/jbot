package net.jbot.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class JBotMenuBar extends JMenuBar {
	
	private ActionListener listener;
	
	public static final String[] TITLES;
	public static final String[][] ELEMENTS;
	
	static {
		TITLES = new String[] { "File" };
		ELEMENTS = new String[][] { { "Project thread (Rune-Server)", "Project Github", "About", "-", "Exit" } };
	}
	
	public JBotMenuBar(ActionListener listener) {
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
