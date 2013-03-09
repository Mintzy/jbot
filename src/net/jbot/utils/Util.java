package net.jbot.utils;

import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import net.jbot.JBot;

public class Util {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-mm-ss");
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

	public static void screenShot(Frame frame) {
		try {
			BufferedImage image = JBot.getRobot().createScreenCapture(
					frame.getBounds());
			String name = getDateFormat().format(new Date()) + ".png";
			ImageIO.write(image, "png", new File("ScreenShots/" + name));
			Util.log("Screen captured and saved as: " + name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void log(String s) {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		String dayName = "";
		switch (day) {
		case 1:
			dayName = "SUNDAY";
			break;
		case 2:
			dayName = "MONDAY";
			break;
		case 3:
			dayName = "TUESDAY";
			break;
		case 4:
			dayName = "WEDNESDAY";
			break;
		case 5:
			dayName = "THURSDAY";
			break;
		case 6:
			dayName = "FRIDAY";
			break;
		case 7:
			dayName = "SATURDAY";
			break;
		}
		System.out.println("[" + dayName + " "+ getTimeFormat().format(new Date()) + "]: " + s);
	}
	
	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	
	public static SimpleDateFormat getTimeFormat() {
		return timeFormat;
	}

}
