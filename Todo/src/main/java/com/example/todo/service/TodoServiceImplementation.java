package com.example.todo.service;


import com.example.todo.model.Todo;
import com.example.todo.repo.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoServiceImplementation implements TodoService{

    private final ToDoRepository toDoRepository;

    @Override
    public void deleteTodo(Long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public void updateTodo(Long id, String title, Boolean isUrgent, Boolean isDone) {
        toDoRepository.findById(id).get().setDone(isDone);
        toDoRepository.findById(id).get().setTitle(title + " <- edited");
        toDoRepository.findById(id).get().setUrgent(isUrgent);
        toDoRepository.saveAndFlush(toDoRepository.findById(id).get());

        toDoRepository.save(new Todo("ale nezabudni",true));
    }
}