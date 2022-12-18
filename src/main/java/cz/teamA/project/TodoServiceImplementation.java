package cz.teamA.project;


import cz.teamA.project.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoServiceImplementation implements TodoService{

    private final CityRepository cityRepository;

    @Override
    public void deleteTodo(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void updateTodo(Long id, String title, Boolean isUrgent, Boolean isDone) {
        cityRepository.findById(id).get().setDone(isDone);
        cityRepository.findById(id).get().setTitle(title + " <- edited");
        cityRepository.findById(id).get().setUrgent(isUrgent);
        cityRepository.saveAndFlush(cityRepository.findById(id).get());

        cityRepository.save(new Todo("ale nezabudni",true));
    }
}