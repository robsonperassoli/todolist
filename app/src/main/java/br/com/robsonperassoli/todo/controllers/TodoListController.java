package br.com.robsonperassoli.todo.controllers;

import static br.com.robsonperassoli.todo.dto.TodoListDTO.listOfDomainToDto;
import static br.com.robsonperassoli.todo.util.Iterables.makeList;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.robsonperassoli.todo.domain.TodoList;
import br.com.robsonperassoli.todo.domain.TodoListRepository;
import br.com.robsonperassoli.todo.dto.TodoListDTO;

@RestController
@RequestMapping("/todo-lists")
public class TodoListController {

	private TodoListRepository todoListRepository;
		
	@Autowired
	public TodoListController(TodoListRepository todoListRepository) {
		this.todoListRepository = todoListRepository;
	}

	@RequestMapping(value="",method=GET)
	List<TodoListDTO> list(){
		List<TodoList> allTodoLists = makeList(todoListRepository.findAll());
		return listOfDomainToDto(allTodoLists);
	}
	
	@RequestMapping(value="/{id}", method=GET)
	ResponseEntity<?> load(@PathVariable Long id){
		TodoList todoList = todoListRepository.findOne(id);
		if(todoList == null)
			return notFound().build();
				
		return ok(todoList);
 	}
	
	@RequestMapping(value="", method=POST)
	ResponseEntity<?> create(@RequestBody TodoListDTO newTodoListDTO){
		TodoList newTodoList = new TodoList(newTodoListDTO.getName());
		todoListRepository.save(newTodoList);
		
		URI resourceUri = fromMethodName(this.getClass(), "load", newTodoList.getId())
				.build().toUri();
		return ResponseEntity.created(resourceUri)
				.build();
	}
	
	@RequestMapping(value="/{id}", method=PUT)
	ResponseEntity<?> update(@RequestBody TodoListDTO todoListDTO, @PathVariable Long id){
		TodoList todoList = todoListRepository.findOne(id);
		if(todoList == null)
			return notFound().build();
		
		todoList.setName(todoListDTO.getName());
		
		todoListRepository.save(todoList);

		return ok().build();
	}
	
	@RequestMapping(value="/{id}", method=DELETE)
	ResponseEntity<?> delete(@PathVariable Long id){
		TodoList todoList = todoListRepository.findOne(id);
		if(todoList == null)
			return notFound().build();
		
		todoListRepository.delete(id);
		
		return ok().build();
	}
}
