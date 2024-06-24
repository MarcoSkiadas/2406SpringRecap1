package org.example.springrecap12406.Controller;

import lombok.RequiredArgsConstructor;
import org.example.springrecap12406.Model.ToDo;
import org.example.springrecap12406.Model.ToDoWithoutID;
import org.example.springrecap12406.Service.RecapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class RecapController {

    private final RecapService recapService;

    @GetMapping()
    public List<ToDo> getToDo() {
        return recapService.getToDo();
    }
@PostMapping()
    public void addToDo(@RequestBody ToDoWithoutID toDo) {
    recapService.addToDo(toDo);
}
    @GetMapping("/{id}")
    public ToDo getDetail(@PathVariable String id) {
        return recapService.getDetail(id);

    }
    @PutMapping("/{id}")
    public void updateToDo(@PathVariable String id, @RequestBody ToDo toDo) {
        recapService.updateToDo(id, toDo);
    }
    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        recapService.deleteToDo(id);
    }


}
