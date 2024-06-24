package org.example.springrecap12406.Controller;

import org.example.springrecap12406.Model.Status;
import org.example.springrecap12406.Model.ToDo;
import org.example.springrecap12406.Repository.ToDoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RecapControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ToDoRepo repo;

    @Test
    void getToDos_shouldReturnAllToDos_whenCalledInitially() throws Exception {
        //GIVEN
      repo.save(new ToDo("To Do 1", "To Do 2", Status.OPEN));
        repo.save(new ToDo("To Do 3", "To Do 4", Status.OPEN));
      //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
[
    {
        "id": "To Do 1",
        "description": "To Do 2",
        "status": "OPEN"
    },
    {
        "id": "To Do 3",
        "description": "To Do 4",
        "status": "OPEN"
    }
    ]
"""));
    }
    @Test
    void addToDo_shouldReturnToDo_whenAdded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
{        "description": "To Do 2",
        "status": "OPEN" }
"""))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void deleteToDo_shouldDeleteToDo_whenDeleted() throws Exception {
repo.save(new ToDo("1", "To Do 2", Status.OPEN));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
{        "description": "To Do 2",
        "status": "OPEN" }
"""))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void updateToDo_shouldUpdateToDo_whenUpdated() throws Exception {
        repo.save(new ToDo("1", "To Do 2", Status.OPEN));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
{        "id": "1",
"description": "To Do 2",
        "status": "IN_PROGRESS" }
"""))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void getToDetail_shouldReturnToDo_whenCalledToDo() throws Exception {
        //GIVEN
        repo.save(new ToDo("1", "To Do 2", Status.OPEN));
        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
    {
        "id": "1",
        "description": "To Do 2",
        "status": "OPEN"
    }
"""));
    }

}