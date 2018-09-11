package com.rollingstone.command;

import java.util.UUID;

import com.rollingstone.command.interfaces.GenericCommand;
import com.rollingstone.domain.Todo;

public class TodoCommand implements GenericCommand {

	UUID id;
	GenericCommandHeader header;
	Todo todo;

	@Override
	public GenericCommandHeader getHeader() {
		return header;
	}

	@Override
	public void setHeader(GenericCommandHeader header) {
		this.header = header;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public TodoCommand(UUID id, GenericCommandHeader header, Todo todo) {
		super();
		this.id = id;
		this.header = header;
		this.todo = todo;
	}

	public TodoCommand() {
		super();
	}

	
}
