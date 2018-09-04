package com.solstice.cloud.messaging.aws;

import com.solstice.command.interfaces.CommandQueueNameResolver;

public class DefaultQueueNameResolver implements CommandQueueNameResolver {

	@Override
	public String resolve(String type) {
		
		return type;
	}

}
