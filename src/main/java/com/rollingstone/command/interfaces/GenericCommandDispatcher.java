package com.rollingstone.command.interfaces;

import java.util.concurrent.Future;

import com.rollingstone.command.GenericCommandResult;


public interface GenericCommandDispatcher {

	 public Future<GenericCommandResult> dispatch(GenericCommand command);
}
