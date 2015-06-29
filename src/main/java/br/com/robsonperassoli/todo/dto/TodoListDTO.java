package br.com.robsonperassoli.todo.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Function;

import br.com.robsonperassoli.todo.domain.TodoList;

public class TodoListDTO {

	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static final Function<TodoList, TodoListDTO> domainToDto = (t) -> {
		TodoListDTO dto = new TodoListDTO();
		dto.setId(t.getId());
		dto.setName(t.getName());
		return dto;
	};
	
	public static List<TodoListDTO> listOfDomainToDto(List<TodoList> todoLists) {
		return todoLists.stream()
				.map(domainToDto)
				.collect(toList());
	}
}
