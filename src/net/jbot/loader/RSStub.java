package net.jbot.loader;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RSStub implements AppletStub {

	private Pattern aPattern = Pattern.compile("archive=(.*) ");
	private Pattern pPattern = Pattern
			.compile("<param name=\"?([^\"]+)\"?\\s+value=\"?([^\"]*)\"?>");
	private Map<String, String> parameters = new HashMap<String, String>();

	public RSStub() {
		String source;
		try {
			source = getPageSource(getCodeBase());
			Matcher aMatcher = aPattern.matcher(source);
			if (aMatcher.find()) {
				Matcher pMatcher = pPattern.matcher(source);
				while (pMatcher.find()) {
					String key = pMatcher.group(1);
					String value = pMatcher.group(2);
					parameters.put(key, value);
				}
				if (source.contains("archive")) {
					parameters.put("archive", source.substring(
							source.indexOf("archive=") + 8,
							source.indexOf(".jar") + 4));
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String getPageSource(URL url) throws IOException,
			InterruptedException {
		URLConnection uc = url.openConnection();
		uc.addRequestProperty(
				"Accept",
				"text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
		uc.addRequestProperty("Accept-Charset",
				"ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		uc.addRequestProperty("Accept-Encoding", "gzip,deflate");
		uc.addRequestProperty("Accept-Language", "en-gb,en;q=0.5");
		uc.addRequestProperty("Connection", "keep-alive");
		uc.addRequestProperty("Host", "www.runescape.com");
		uc.addRequestProperty("Keep-Alive", "300");
		uc.addRequestProperty(
				"User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.8.0.6) Gecko/20060728 Firefox/1.5.0.6");
		DataInputStream di = new DataInputStream(uc.getInputStream());
		byte[] tmp = new byte[uc.getContentLength()];
		di.readFully(tmp);
		di.close();
		return new String(tmp);
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public URL getDocumentBase() {
		return getCodeBase();
	}

	@Override
	public URL getCodeBase() {
		try {
			return new URL("http://oldschool20.runescape.com/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getParameter(String name) {
		return parameters.get(name);
	}

	@Override
	public AppletContext getAppletContext() {
		return null;
	}

	@Override
	public void appletResize(int width, int height) {

	}

}
