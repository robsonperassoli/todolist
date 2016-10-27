package br.com.robsonperassoli.todo.controllers;

import static br.com.robsonperassoli.todo.dto.TaskDTO.listOfDomainToDto;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.robsonperassoli.todo.domain.Task;
import br.com.robsonperassoli.todo.domain.TaskRepository;
import br.com.robsonperassoli.todo.domain.TodoList;
import br.com.robsonperassoli.todo.domain.TodoListRepository;
import br.com.robsonperassoli.todo.dto.TaskDTO;

@RestController
@RequestMapping("/todo-lists/{id}/tasks")
public class TaskController {

	private TodoListRepository todoListRepository;
	private TaskRepository taskRepository;
	
	@Autowired
	public TaskController(TodoListRepository todoListRepository,
			TaskRepository taskRepository) {
		this.todoListRepository = todoListRepository;
		this.taskRepository = taskRepository;
	}

	@RequestMapping(value="",method=GET)
	public List<TaskDTO> list(@PathVariable Long id){
		return listOfDomainToDto(taskRepository.findByTodoListId(id));
	}
	
	@RequestMapping(value="/{taskId}", method=GET)
	ResponseEntity<?> load(@PathVariable Long id, @PathVariable Long taskId){
		Task task = taskRepository.findTaskById(id, taskId);
		if(task == null)
			return notFound().build();
				
		return ok(task);
 	}
	
	@RequestMapping(value="", method=POST)
	public ResponseEntity<?> create(@PathVariable Long id, @RequestBody @Valid TaskDTO newTaskDTO){
		TodoList todoList = todoListRepository.findOne(id);
		if(todoList == null)
			return notFound().build();
		
		Task task = new Task(todoList, newTaskDTO.getDescription(), newTaskDTO.getDone());
		taskRepository.save(task);
		
		URI resourceUri = fromMethodName(this.getClass(), "load", todoList.getId(), task.getId())
				.build().toUri();
		return ResponseEntity.created(resourceUri)
				.build();
	}
	
	@RequestMapping(value="/{taskId}", method=PUT)
	public ResponseEntity<?> update(@PathVariable Long id, @PathVariable Long taskId, @RequestBody @Valid TaskDTO taskDTO){
		Task task = taskRepository.findTaskById(id, taskId);
		if(task == null)
			return notFound().build();
		
		task.setDescription(taskDTO.getDescription());
		task.setDone(taskDTO.getDone());

		taskRepository.save(task);

		return ok().build();
	}
	
	@RequestMapping(value="/{taskId}", method=DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable Long taskId){
		Task task = taskRepository.findTaskById(id, taskId);
		if(task == null)
			return notFound().build();
		
		taskRepository.delete(taskId);
		
		return ok().build();
	}
}
