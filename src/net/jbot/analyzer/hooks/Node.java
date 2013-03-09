package net.jbot.analyzer.hooks;

public interface Node {
	
	public long getUID();
	
	public Node getPrevious();
	
	public Node getNext();

}
