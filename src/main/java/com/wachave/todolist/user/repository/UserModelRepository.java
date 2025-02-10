package com.wachave.todolist.user.repository;

import com.wachave.todolist.user.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, UUID> {

   UserModel findByUsername(String username);
}
