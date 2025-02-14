package com.wachave.todolist.task.service;

import com.wachave.todolist.task.entity.TaskModel;
import com.wachave.todolist.task.repository.TaskModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskModelServiceImpl implements TaskModelService{

    @Autowired
    private TaskModelRepository taskModelRepository;

    @Override
    public TaskModel create(TaskModel taskModel) {

       TaskModel tasks = this.taskModelRepository.save(taskModel);

        return tasks;
    }

    @Override
    public List<TaskModel> findByIdUser(UUID idUser) {

        List<TaskModel> tasks = this.taskModelRepository.findByIdUser((UUID)idUser);

        return tasks;
    }
}
