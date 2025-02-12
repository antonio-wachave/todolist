package com.wachave.todolist.task.controller;

import com.wachave.todolist.task.entity.TaskModel;
import com.wachave.todolist.task.service.TaskModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tasks")
@RestController
public class TaskModelController {

    @Autowired
    private TaskModelService taskModelService;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel){

        TaskModel tasks = this.taskModelService.create(taskModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada com sucesso!");
    }

}
