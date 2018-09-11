package com.solstice.api;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.solstice.command.GenericCommandHeader;
import com.solstice.command.GenericCommandType;
import com.solstice.command.TodoCommand;
import com.solstice.command.interfaces.GenericCommandBus;
import com.solstice.domain.Todo;
import com.solstice.service.TodoService;


@RestController
public class TodoController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final String SCHEMA_VERSION = "1.0";

	private final GenericCommandBus commandBus;
	private TodoService todoService;
	private static Validator validator;

	@Autowired
	public TodoController(GenericCommandBus commandBus, TodoService todoService) {
		this.commandBus = commandBus;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		this.todoService = todoService;
	}

	@GetMapping("solstice-ecommerce/todo")
	@ResponseBody
	public List<Todo> getTodos(){
		return todoService.getAllTodos();
	}
	
	@GetMapping("solstice-ecommerce/todo/{id}")
	@ResponseBody
	public Todo getTodo(@RequestParam Long todoId){
		return todoService.getTodo(todoId);
	}
	
	
	@PostMapping("solstice-ecommerce/todo")
	public ResponseEntity<?> createTodo(@RequestBody Todo todo) {

		log.info("Received request to create Todo");
		Set<ConstraintViolation<Todo>> constraintViolations = validator.validate(todo);

		String errorMessage = buildErrorMessage(constraintViolations);

		if (!errorMessage.isEmpty()) {
			log.error("Error when creating product rating: " + errorMessage);
			return ResponseEntity.unprocessableEntity().body(errorMessage);
		} else {
			/*
			 * The Command is a standard design pattern through which data and code is transferred from the client to the processing server. In 
			 * this case, we are not sending any code / instructions but we are using the command patterns to send data along with a message header i.e. CommandHeader
			 * to be processed by the Listener listening to the Message Queue.
			 */
			TodoCommand todoCommand = new TodoCommand();
			todoCommand.setTodo(todo);
			todoCommand.setId(UUID.randomUUID());
			
			/* 
			 * The Header value will determine which Message broker queue the message will be sent. We have multiple Queues for processing certain types
			 * of messafes. For example, we have one Queue for processing products and another for processing payments.
			 * Individual Queue depth i.e. how many messages are in each Queue can trigger Amazon Web Service AutoScaling, for example.
			 * 
			 */
			GenericCommandHeader header = new GenericCommandHeader(GenericCommandType.CREATE_TODO.toString(),
					SCHEMA_VERSION, new Timestamp(System.currentTimeMillis()));
			todoCommand.setHeader(header);
			
			/*
			 * The GenericCommandBus is an interface which hides the queue message sending details from the rest of the application
			 * The class DispatchingCommandBus implements the interface GenericCommandBus.
			 * The next class to visit thus, is the DispatchingCommandBus.java
			 */
			commandBus.send(todoCommand);
			
			log.info("Todo sent to queue for creation");
			return ResponseEntity.ok("Sent to Todo Create Queue");
		}

	}

	
	@PutMapping("solstice-ecommerce/todo/{id}")
	ResponseEntity<?> updateTodo(@RequestBody Todo todo) {

		log.info("Received request to update product rating");
		Set<ConstraintViolation<Todo>> constraintViolations = validator.validate(todo);
		String errorMessage = buildErrorMessage(constraintViolations);
		if (!errorMessage.isEmpty())
		{
			log.error("Error when updating Todo: " + errorMessage);
			return ResponseEntity.unprocessableEntity().body(errorMessage);
		}
		else
		{
			TodoCommand command = new TodoCommand();
			command.setTodo(todo);
			command.setId(UUID.randomUUID());
			GenericCommandHeader header = new GenericCommandHeader(GenericCommandType.UPDATE_TODO.toString(),SCHEMA_VERSION, new Timestamp(System.currentTimeMillis()));
			command.setHeader(header);
			commandBus.send(command);
			log.info("Todo sent to queue for update");
			return ResponseEntity.ok("Sent to Update Queue");
		}

	}
	
	@DeleteMapping("solstice-ecommerce/todo/{id}")
	@ResponseBody
	public Todo deleteTodo(@RequestParam Long todoId){
		return todoService.deleteTodo(todoId);
	}
	
	private String buildErrorMessage(Set<ConstraintViolation<Todo>> constraintViolations) {
		String message = "";
		if (constraintViolations == null || constraintViolations.size() == 0)
			return message;
		else {
			for (ConstraintViolation constraintViolation : constraintViolations) {
				message += constraintViolation.getMessage() + " ";
			}
			return message;
		}
	}
}
