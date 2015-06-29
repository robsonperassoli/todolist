package br.com.robsonperassoli.todo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="todo_list")
public class TodoList {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@NotEmpty
	private String name;
	
	@OneToMany(mappedBy="todoList", cascade=CascadeType.REMOVE)
	private List<Task> tasks;
	
	protected TodoList() {}

	public TodoList(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
