package com.rollingstone.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

@Aspect
@Component
public class RestControllerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	Counter genericAPICallCounter = Metrics.counter("com.rollingstone.api.generic.controller.counter");

	Counter createTodoCounter = Metrics.counter("com.rollingstone.api.TodoController.createTodo");

	Counter getTodosCounter = Metrics.counter("com.rollingstone.api.TodoController.getTodos");

	Counter getTodoCounter = Metrics.counter("com.rollingstone.api.TodoController.getTodo");

	Counter updateTodoCounter = Metrics.counter("com.rollingstone.api.TodoController.updateTodo");

	Counter deleteTodoCounter = Metrics.counter("com.rollingstone.api.TodoController.deleteTodo");

	@Before("execution(public * com.rollingstone.controller.*Controller.*(..))")
	public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
		logger.info(":::::AOP Before for Any Controller REST call:::::" + pjp);
		genericAPICallCounter.increment();
	}

	@AfterReturning("execution(public * com.rollingstone.api.*Controller.createTodo*(..))")
	public void afterCallingCreateTodo(JoinPoint pjp) {
		logger.info(":::::AOP @AfterReturning Create Todo REST call:::::" + pjp);
		createTodoCounter.increment();
	}

	@AfterReturning("execution(public * com.rollingstone.api.*Controller.getTodos*(..))")
	public void afterCallingGetTodos(JoinPoint pjp) {
		logger.info(":::::AOP @AfterReturning getTodos REST call:::::" + pjp);
		getTodosCounter.increment();
	}

	@AfterReturning("execution(public * com.rollingstone.api.*Controller.getTodo*(..))")
	public void afterCallingGetTodo(JoinPoint pjp) {
		logger.info(":::::AOP @AfterReturning getTodo REST call:::::" + pjp);
		getTodoCounter.increment();
	}

	@AfterReturning("execution(public * com.rollingstone.api.*Controller.updateTodo*(..))")
	public void afterCallingUpdateTodo(JoinPoint pjp) {
		logger.info(":::::AOP @AfterReturning updateTodo REST call:::::" + pjp);
		updateTodoCounter.increment();
	}

	@AfterReturning("execution(public * com.rollingstone.api.*Controller.deleteTodo*(..))")
	public void afterCallingDeleteTodo(JoinPoint pjp) {
		logger.info(":::::AOP @AfterReturning deleteTodo REST call:::::" + pjp);
		deleteTodoCounter.increment();
	}

}
