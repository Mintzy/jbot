package net.jbot.hooks;

public interface NodeHook {
	
	public long getId();

	public NodeHook getPrevious();

	public NodeHook getNext();

}