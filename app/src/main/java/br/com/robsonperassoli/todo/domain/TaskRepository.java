package br.com.robsonperassoli.todo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	@Query("select t from #{#entityName} t where t.todoList.id = :todoListId and t.id = :taskId")
	Task findTaskById(@Param("todoListId") Long todoListId, @Param("taskId") Long taskId);

	List<Task> findByTodoListId(Long id);
	
}
