package com.wachave.todolist.task.service;

import com.wachave.todolist.task.entity.TaskModel;

import java.util.List;
import java.util.UUID;

public interface TaskModelService {

    TaskModel create(TaskModel taskModel);

    List<TaskModel> findByIdUser(UUID idUser);
}
