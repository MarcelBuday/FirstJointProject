package cz.teamA.project;

import cz.teamA.project.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TodoController {

//    private final CityRepository cityRepository;
//    private final TodoService todoService;
//
//
//    //@ResponseBody
//    @GetMapping({"/todo", "/"})
//    public String list(@RequestParam(required = false) String isActive, Model model) {
//
//        model.addAttribute("isActive", isActive);
//        model.addAttribute("todos", cityRepository.findAll());
//        return "todolist";
//
//    }
//
//    @GetMapping("/update/{id}")
//    public ModelAndView update(@PathVariable(name = "id") Long id) {
//        ModelAndView editView = new ModelAndView("update");
//        Todo todo = cityRepository.findById(id).get();
//        editView.addObject("todo", todo);
//
//        return editView;
//
//    }
//
//
//    @GetMapping("/delete/{id}")
//    public String deleteTodo(@PathVariable(name = "id") Long id) {
//        cityRepository.deleteById(id);
//        return "redirect:/";
//    }
//
//    @GetMapping("/todo/add")
//    public String getTodo() {
//        return "todo_app";
//    }
//
//    @GetMapping("/update")
//    public String getUpdate() {
//        return "update";
//    }
//
//    @PostMapping("/update/{id}")
//    public String postUpdate(@RequestParam String title, boolean done, boolean urgent, @PathVariable Long id) {
//        todoService.updateTodo(id,title,urgent,done);
//        return "redirect:/todo";
//    }
//
//    @PostMapping("/todo/add")
//    public String addTodo(@RequestParam(name = "todo") String todo) {
//        cityRepository.save(new Todo(todo, false));
//        return "redirect:/todo";
//    }
}
