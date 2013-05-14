package net.jbot.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WebUtil {

	public static void downloadGamepack(URL url) {
		try {
			url.openConnection();
			InputStream reader = url.openStream();
			FileOutputStream writer = new FileOutputStream("gamepack.jar");
			byte[] buffer = new byte[153600];
			int read = 0;
			while ((read = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, read);
				buffer = new byte[153600];
			}
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
