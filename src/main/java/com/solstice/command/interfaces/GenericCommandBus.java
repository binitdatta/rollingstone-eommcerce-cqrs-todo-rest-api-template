package com.solstice.command.interfaces;

import java.util.concurrent.Future;

import com.solstice.command.GenericCommandResult;


public interface GenericCommandBus {

	 <T extends GenericCommand> Future<GenericCommandResult> send(T command);
}
