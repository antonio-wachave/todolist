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

       return this.taskModelRepository.save(taskModel);
    }

    @Override
    public List<TaskModel> findByIdUser(UUID idUser) {

        return this.taskModelRepository.findByIdUser((UUID)idUser);

    }

    @Override
    public TaskModel update(TaskModel taskModel) {

        return this.taskModelRepository.save(taskModel);
    }
}
