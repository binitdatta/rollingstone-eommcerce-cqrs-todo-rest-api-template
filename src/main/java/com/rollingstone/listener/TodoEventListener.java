package com.rollingstone.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rollingstone.service.event.TodoServiceEvent;


@Component
public class TodoEventListener implements ApplicationListener<TodoServiceEvent> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@EventListener
	public void onApplicationEvent(TodoServiceEvent event) {
		TodoServiceEvent todoEvent = (TodoServiceEvent) event;
		logger.info("Address Event Listener " + todoEvent.getEventType() + " with details : " 
		+ todoEvent.getEventTodo().toString());
	}
}
