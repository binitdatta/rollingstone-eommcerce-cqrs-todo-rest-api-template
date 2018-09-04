package com.solstice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.solstice.domain.Todo;

public interface TodoRepository extends PagingAndSortingRepository <Todo, Long>{

}
