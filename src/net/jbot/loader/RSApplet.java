package net.jbot.loader;

import java.applet.Applet;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

@SuppressWarnings("serial")
public class RSApplet extends Applet {

	private Applet loader;
	private URL jarUrl;

	public ClassLoader getClasses() throws MalformedURLException {
		return new URLClassLoader(new URL[] { jarUrl });
	}

	public Applet loadRS() throws Exception {
		RSStub stub = new RSStub();
		jarUrl = new URL(stub.getCodeBase() + stub.getParameter("archive"));
		Class<?> clientClass = getClasses().loadClass("client");
		loader = (Applet) clientClass.newInstance();
		loader.setStub(stub);
		loader.init();
		loader.start();
		return loader;
	}

	public void paint(Graphics g) {
		g.drawString("[RuneScape Applet would go here]", 10, 50);
	}

}
