package net.jbot.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.jbot.Constants;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5818402520180364979L;

	private MainPanel panel = new MainPanel();
	private BotMenuBar menuBar;

	public void buildFrame() {
		panel.getConsole().log(new LogRecord(Level.INFO, "Welcome to " + Constants.NAME + "."));
		setTitle(Constants.TITLE);
		setPreferredSize(new Dimension(765, 670));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		menuBar = new BotMenuBar(this);
		setJMenuBar(menuBar);
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equalsIgnoreCase("exit")) {
			int option = JOptionPane.showConfirmDialog(this,
					"Are you sure you want to exit " + Constants.NAME + "?",
					"Exit", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

		if (action.equalsIgnoreCase("about")) {
			JOptionPane
					.showMessageDialog(
							this,
							Constants.NAME
									+ " is made possible by the RuneScape client hacking communities such as MITB and JavaHacking.\nThis project was made to learn about RuneScape client hacking and the process of making a RuneScape bot.\n"
									+ Constants.NAME
									+ " was created as a solo project using many resources of said client hacking communities by Justin (Mr. Brightside).\nThank you for your interest in "
									+ Constants.NAME
									+ " and RuneScape client hacking.",
							"About", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (action.equalsIgnoreCase("load bot")) {
			panel.loadBot();
		}

	}

}
