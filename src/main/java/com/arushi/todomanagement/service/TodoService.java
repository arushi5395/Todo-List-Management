package com.arushi.todomanagement.service;

import com.arushi.todomanagement.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodoById(Long id);
    List<TodoDto> getAllTodos();
    TodoDto updateTodoById(Long id, TodoDto updatedTodo);
    void deleteTodoById(Long id);
    TodoDto completeTodo(Long id);
    TodoDto inCompleteTodo(Long id);
}
