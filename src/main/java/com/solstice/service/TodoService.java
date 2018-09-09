package com.solstice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.solstice.domain.Todo;
import com.solstice.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public List<Todo> getAllTodos() {
		Iterable<Todo> todos = todoRepository.findAll();
		
		List<Todo> todoList = toList(todos);
		
		return todoList;
	}
	
	public Todo getTodo(Long todoId) {
		Optional<Todo> todoOptional = todoRepository.findById(todoId);
		Todo todo = todoOptional.get();
		
		return todo;
	}
	
	public static <T> List<T> toList(final Iterable<T> iterable) {
	    return StreamSupport.stream(iterable.spliterator(), false)
	                        .collect(Collectors.toList());
	}

}
