package com.solstice.cloud.messaging.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.solstice.command.GenericCommandType;
import com.solstice.command.interfaces.CommandQueueNameResolver;

/**
 * Created by bdatta on 12/27/16.
 */
@Component
public class SQSCommandQueueNameResolver implements CommandQueueNameResolver {
    @Value("${queue.debug}")
    boolean debug;

    @Override
    public String resolve(String type) {
        if (this.debug) {
            return CommandQueues.CommandQueuesMap.get(GenericCommandType.DEBUG.toString());
        }
        return CommandQueues.CommandQueuesMap.get(type);
    }
}
