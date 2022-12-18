package com.example.todo.service;

public interface TodoService {
    void deleteTodo(Long id);
    void updateTodo(Long id, String title, Boolean isUrgent,Boolean isDone );
}