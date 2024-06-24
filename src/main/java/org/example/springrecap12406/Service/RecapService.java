package org.example.springrecap12406.Service;

import lombok.RequiredArgsConstructor;
import org.example.springrecap12406.Model.Status;
import org.example.springrecap12406.Model.ToDo;
import org.example.springrecap12406.Model.ToDoWithoutID;
import org.example.springrecap12406.Repository.ToDoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecapService {

    private final IdService idService;
    private final ToDoRepo toDoRepo;


    public void addToDo(ToDoWithoutID toDo) {
        ToDo newToDo = new ToDo(idService.generateId(), toDo.description(), Status.OPEN);
        toDoRepo.save(newToDo);

    }

    public List<ToDo> getToDo() {
        return toDoRepo.findAll();
    }

    public ToDo getDetail(String id) {
        Optional<ToDo> foundToDo = toDoRepo.findById(id);
        return foundToDo.orElseThrow();
    }

    public void updateToDo(String id, ToDo toDo) {
        Optional<ToDo> foundToDo = toDoRepo.findById(id);
        if (foundToDo.isPresent()) {
            ToDo updateToDo = foundToDo.orElseThrow().withStatus(toDo.status()).withDescription(toDo.description());
            toDoRepo.save(updateToDo);
        }
    }

    public void deleteToDo(String id) {
        toDoRepo.deleteById(id);
    }
}
