package net.jbot;

import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.jbot.gui.JBotFrame;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.skin.MagellanSkin;

public class JBot {
	
	private static Robot robot;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					SubstanceLookAndFeel.setSkin(new MagellanSkin());
					JBotFrame frame = new JBotFrame();
					frame.buildFrame();
					robot = new Robot();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	
	public static Robot getRobot() {
		return robot;
	}

}
