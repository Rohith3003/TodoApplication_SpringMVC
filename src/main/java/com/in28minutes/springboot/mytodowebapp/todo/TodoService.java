package com.in28minutes.springboot.mytodowebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static int todosCount = 0;

	private static List<Todo> todos = new ArrayList<Todo>();

	static {
		todos.add(new Todo(++todosCount, "in28minutes", "Learn AWS Dev", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn Devops", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn Full Stack Dev", LocalDate.now().plusYears(3), false));
	}

	public List<Todo> findByUserName(String userName) {
		Predicate<? super Todo> predicate = (todo) -> todo.getUsername().equalsIgnoreCase(userName);
		return todos.stream().filter(predicate).collect(Collectors.toList());
	}

	public void addTodo(String username, String description, LocalDate targetDate, boolean isTaskDone) {
		Todo newTodo = new Todo(++todosCount, username, description, targetDate, isTaskDone);
		todos.add(newTodo);
	}

	public void deleteTodoById(int id) {
		Predicate<? super Todo> predicate = (todo) -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = (todo) -> todo.getId() == id;
		return todos.stream().filter(predicate).findFirst().get();
	}

	public void updateTodo(Todo todoNew) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate = (todo) -> todo.getId() == todoNew.getId();
		Todo todoToUpdate = todos.stream().filter(predicate).findFirst().get();
		todoToUpdate.setDescription(todoNew.getDescription());
		todoToUpdate.setTargetDate(todoNew.getTargetDate());
		todoToUpdate.setDone(todoNew.isDone());
	}
}
