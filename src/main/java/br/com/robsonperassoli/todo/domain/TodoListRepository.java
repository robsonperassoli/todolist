package br.com.robsonperassoli.todo.domain;

import org.springframework.data.repository.CrudRepository;


public interface TodoListRepository extends CrudRepository<TodoList, Long> {


}
