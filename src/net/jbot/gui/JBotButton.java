package net.jbot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class JBotButton extends JComponent {
	
	public JBotButton(final ActionListener listener) {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				listener.actionPerformed(new ActionEvent(this, e.getID(), "Screen Shot"));
			}
		});
	}

}
