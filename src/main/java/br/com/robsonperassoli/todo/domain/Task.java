package br.com.robsonperassoli.todo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String description;
	
	@Column
	private Boolean done;
	
	@ManyToOne
	private TodoList todoList;
	
	protected Task() {}

	public Task(TodoList todoList, String description, Boolean done) {
		this.todoList = todoList;
		this.description = description;
		this.done = done;
	}
	
	public void setDone(Boolean done) {
		this.done = done;
	}
	
	public Long getId() {
		return id;
	}
	
	public Boolean getDone() {
		return done;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
