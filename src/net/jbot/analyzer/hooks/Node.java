package net.jbot.analyzer.hooks;

public interface Node {
	
	long getUID();
	
	Node getPrevious();
	
	Node getNext();

}
