package com.solstice.cloud.messaging.aws;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.solstice.command.GenericCommandResult;
import com.solstice.command.interfaces.CommandQueueNameResolver;
import com.solstice.command.interfaces.GenericCommand;
import com.solstice.command.interfaces.GenericCommandDispatcher;

@Component
public class SQSCommandDispatcher implements GenericCommandDispatcher {

	private final SQSQueueSender sqsQueueSender;
	
	/*
	 * There are two classes that implements the Interface CommandQueueNameResolver, namely the DefaultQueueNameResolver and the 
	 * SQSCommandQueueNameResolver. The second one is annotated with @Component so that is the one being use for practical purposes.
	 * So the next class to visit is the SQSCommandQueueNameResolver.java
	 */
	
	private final CommandQueueNameResolver queueResolver;

	@Autowired
	public SQSCommandDispatcher(SQSQueueSender sqsQueueSender, CommandQueueNameResolver queueResolver) {
		this.sqsQueueSender = sqsQueueSender;
		this.queueResolver = queueResolver;
	}

	@Override
	public Future<GenericCommandResult> dispatch(GenericCommand command) {
		// Map
		String type = command.getHeader().getCommandType();

		// Map type to queue name
		String queue;
		if (queueResolver == null || queueResolver.resolve(type) == null)
			queue = new DefaultQueueNameResolver().resolve(type);
		else
			queue = queueResolver.resolve(type);

		return new AsyncResult<>(this.sqsQueueSender.send(queue, command));
	}
}
