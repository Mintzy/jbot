package net.jbot.utils;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class WebUtil {

	public static void downloadGamepack(String file) throws IOException {
		long startTime, endTime, totalTime;
		startTime = System.currentTimeMillis();
		URL url = new URL(file);
		url.openConnection();
		InputStream reader = url.openStream();
		FileOutputStream writer = new FileOutputStream("runescape.jar");
		byte[] buffer = new byte[153600];
		int amountRead = 0;
		int read = 0;
		while ((read = reader.read(buffer)) > 0) {
			writer.write(buffer, 0, read);
			buffer = new byte[153600];
			amountRead += read;
		}
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Downloaded " + (new Integer(amountRead).toString())
				+ " bytes (" + totalTime + "ms)");
		writer.close();
		reader.close();
	}

	/**
	 * Credits to: Deadly Uzi for this.
	 */
	public static String findGamepack(String source) {
		String archiveParam = "archive=";
		int gamepack = source.indexOf(archiveParam);
		return source.substring(gamepack + archiveParam.length(),
				source.indexOf('\'', gamepack));
	}

	public static String readPage(String url) throws IOException {
		URL page = new URL(url);
		URLConnection pageConnection = page.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				pageConnection.getInputStream(), "UTF-8"));
		String line;
		StringBuilder source = new StringBuilder();
		while ((line = reader.readLine()) != null)
			source.append(line);
		reader.close();
		return source.toString();
	}

	public static void openURL(String url) {
		try {
			Util.log("Launching url: " + url);
			Desktop desktop = Desktop.getDesktop();
			URI uri = new URI(url);
			desktop.browse(uri.resolve(uri));
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}

	}

}
