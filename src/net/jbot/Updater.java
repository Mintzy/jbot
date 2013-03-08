package net.jbot;

import java.io.IOException;

import net.jbot.utils.WebUtil;

public class Updater {
	
	public static void main(String[] args) {
		System.out.println("Starting JBot Updater..");
		new Updater();
	}
	
	private Updater() {
		try {
			//First, lets read the page and obtain the source
			String source = WebUtil.readPage("http://oldschool20.runescape.com");
			//Next, lets find the gamepack from the source
			String gamepack = WebUtil.findGamepack(source);
			String gamepackURL = "http://oldschool20.runescape.com/" + gamepack;
			//Now, lets download the gamepack archive
			WebUtil.downloadGamepack(gamepackURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
