package com.solstice.command.interfaces;

import java.util.concurrent.Future;

import com.solstice.command.GenericCommandResult;


public interface GenericCommandDispatcher {

	 public Future<GenericCommandResult> dispatch(GenericCommand command);
}
