package net.jbot.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class JBotToolBar extends JToolBar {

	private JButton screenShotButton;
	
	public JBotToolBar(ActionListener listener) {
		screenShotButton = new JButton("Screen Shot");
		screenShotButton.addActionListener(listener);
		screenShotButton.setFocusable(false);
		
		setFloatable(false);
		add(new JBotButton(listener));
		add(screenShotButton);
	}
	
}
