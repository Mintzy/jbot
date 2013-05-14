package net.jbot.loader;

import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

@SuppressWarnings("serial")
public class RSLoader extends Applet {

	private Applet applet;
	private URL jarUrl;

	public ClassLoader getClasses() throws MalformedURLException {
		return new URLClassLoader(new URL[] { jarUrl });
	}

	public Applet loadRS() throws Exception {
		RSStub stub = new RSStub();
		//WebUtil.downloadGamepack(new URL(stub.getCodeBase() + stub.getParameter("archive")));
		jarUrl = new File("out.jar").toURI().toURL(); //new URL(stub.getCodeBase() + stub.getParameter("archive"));
		Class<?> clientClass = getClasses().loadClass("client");
		applet = (Applet) clientClass.newInstance();
		applet.setStub(stub);
		applet.init();
		applet.start();
		return applet;
	}
	
	public Applet getApplet() {
		return applet;
	}

}
