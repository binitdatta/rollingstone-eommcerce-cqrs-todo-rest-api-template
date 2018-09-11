package com.rollingstone.service.event;

import org.springframework.context.ApplicationEvent;

import com.rollingstone.domain.Todo;

/**
 * This is an optional class used in publishing application events. This can be
 * used to inject events into the Spring Boot audit management endpoint.
 */
public class TodoServiceEvent extends ApplicationEvent {
	
	Todo eventTodo;
	String eventType;
	
	public TodoServiceEvent(Object source) {
		super(source);
	}

	public String toString() {
		return "My AccountService Event";
	}


	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Todo getEventTodo() {
		return eventTodo;
	}

	public void setEventTodo(Todo eventTodo) {
		this.eventTodo = eventTodo;
	}

	public TodoServiceEvent(Object source, Todo eventTodo, String eventType) {
		super(source);
		this.eventTodo = eventTodo;
		this.eventType = eventType;
	}

	
	
}
