package net.jbot.console;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class ConsoleOutputStream extends OutputStream {

	private JTextArea jta;
	
	public ConsoleOutputStream(JTextArea jta) {
		this.jta = jta;
	}
	
	@Override
	public void write(int b) throws IOException {
		jta.append(String.valueOf((char) b));

	}

}
