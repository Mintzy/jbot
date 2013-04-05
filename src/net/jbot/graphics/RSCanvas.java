package net.jbot.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class RSCanvas extends Canvas {
	
	private BufferedImage gameBuffer = new BufferedImage(765, 503, BufferedImage.TYPE_INT_RGB);

    @Override
    public Graphics getGraphics() {
        final Graphics g = gameBuffer.getGraphics();
        g.setColor(Color.GREEN);
        g.drawString("jBot", 10, 50);
        super.getGraphics().drawImage(gameBuffer, 0, 0, null);
        return g;
    }

}
