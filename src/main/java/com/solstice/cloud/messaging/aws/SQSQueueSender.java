package com.solstice.cloud.messaging.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.solstice.command.GenericCommandResult;

@Component
public class SQSQueueSender {

	/*
	 * Comes from Spring Cloud AWS Messaging Library
	 */
	private final QueueMessagingTemplate queueMessagingTemplate;

	@Autowired
	public SQSQueueSender(AmazonSQSAsync amazonSqs) {
		this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);
	}

	public GenericCommandResult send(String queue, Object message) {
		this.queueMessagingTemplate.convertAndSend(queue, message);
		GenericCommandResult cr = new GenericCommandResult();
		cr.setAsSuccessful();
		return cr;
	}
}
