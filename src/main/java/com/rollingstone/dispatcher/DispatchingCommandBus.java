package com.rollingstone.dispatcher;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rollingstone.command.GenericCommandResult;
import com.rollingstone.command.interfaces.GenericCommand;
import com.rollingstone.command.interfaces.GenericCommandBus;
import com.rollingstone.command.interfaces.GenericCommandDispatcher;

/**
 * Created by bdatta on 12/27/16.
 */
@Component
public class DispatchingCommandBus implements GenericCommandBus {
	
	/*
	 * Look into the SQSCommandDispatcher which implements the GenericCommandDispatcher Interface
	 */
	private final GenericCommandDispatcher commandDispatcher;

	@Autowired
	public DispatchingCommandBus(GenericCommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
	}

	@Override
	public <T extends GenericCommand> Future<GenericCommandResult> send(T command) {
		return this.commandDispatcher.dispatch(command);
	}
}
