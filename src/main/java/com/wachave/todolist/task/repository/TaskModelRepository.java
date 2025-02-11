package com.wachave.todolist.task.repository;

import com.wachave.todolist.task.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskModelRepository extends JpaRepository<TaskModel, UUID> {
}
