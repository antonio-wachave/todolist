package com.wachave.todolist.task.controller;

import com.wachave.todolist.task.entity.TaskModel;
import com.wachave.todolist.task.service.TaskModelService;
import com.wachave.todolist.user.entity.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestMapping("/tasks")
@RestController
public class TaskModelController {

    @Autowired
    private TaskModelService taskModelService;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){

        var idUser = request.getAttribute("idUser");

        taskModel.setIdUser((UUID) idUser);

        LocalDateTime currentDate = LocalDateTime.now();

        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" A data de Inicio/ data de termino deve ser maior que a data actual.");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" A data de Inicio deve ser menor do que a data de termino. ");
        }

        TaskModel tasks = this.taskModelService.create(taskModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(tasks);
    }


    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){

        var idUser = request.getAttribute("idUser");

        List<TaskModel> tasks = this.taskModelService.findByIdUser((UUID)idUser);

        return  tasks;
    }

}
