package br.com.robsonperassoli.todo.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.robsonperassoli.todo.domain.Task;

public class TaskDTO {

	private Long id;
	
	@NotEmpty
	private String description;
	
	@NotNull
	private Boolean done;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getDone() {
		return done;
	}
	
	public void setDone(Boolean done) {
		this.done = done;
	}
	
	public static final Function<Task, TaskDTO> domainToDto = (t) -> {
		TaskDTO dto = new TaskDTO();
		dto.setDescription(t.getDescription());
		dto.setDone(t.getDone());
		dto.setId(t.getId());
		return dto;
	};
	
	public static List<TaskDTO> listOfDomainToDto(List<Task> tasks) {
		return tasks.stream()
				.map(domainToDto)
				.collect(toList());
	}
	
}
