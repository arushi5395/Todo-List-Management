package com.arushi.todomanagement.service.impl;

import com.arushi.todomanagement.dto.TodoDto;
import com.arushi.todomanagement.entity.Todo;
import com.arushi.todomanagement.exception.ResourceNotFoundException;
import com.arushi.todomanagement.repository.TodoRepository;
import com.arushi.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);

        Todo savedTodo = todoRepository.save(todo);

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Todo does not exist with given id: "+ id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodoById(Long id, TodoDto updatedTodo) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Todo does not exist for given id: " + id));
        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());
        todo.setCompleted(updatedTodo.isCompleted());

        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found:" + id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Todo not found:"+ id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Todo not found:"+ id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
