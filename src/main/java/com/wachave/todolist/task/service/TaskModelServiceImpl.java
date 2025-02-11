package com.wachave.todolist.task.service;

import com.wachave.todolist.task.entity.TaskModel;
import com.wachave.todolist.task.repository.TaskModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskModelServiceImpl implements TaskModelService{

    @Autowired
    private TaskModelRepository taskModelRepository;

    @Override
    public TaskModel create(TaskModel taskModel) {

       TaskModel tasks = this.taskModelRepository.save(taskModel);

        return tasks;
    }
}
