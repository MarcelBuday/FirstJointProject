package com.example.todo.repo;

import com.example.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<Todo,Long> {
}
