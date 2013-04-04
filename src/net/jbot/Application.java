package net.jbot;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.skin.MagellanSkin;

import net.jbot.gui.MainFrame;

public class Application {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JPopupMenu.setDefaultLightWeightPopupEnabled(false);
				SubstanceLookAndFeel.setSkin(new MagellanSkin());
				MainFrame frame = new MainFrame();
				frame.buildFrame();
			}
		});
	}

}
