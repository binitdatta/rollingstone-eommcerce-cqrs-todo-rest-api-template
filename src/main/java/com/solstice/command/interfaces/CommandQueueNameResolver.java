package com.solstice.command.interfaces;

public interface CommandQueueNameResolver {

	String resolve(String commandType);
}
