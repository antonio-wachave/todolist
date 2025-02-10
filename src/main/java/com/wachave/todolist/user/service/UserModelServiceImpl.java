package com.wachave.todolist.user.service;

import com.wachave.todolist.user.entity.UserModel;
import com.wachave.todolist.user.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserModelServiceImpl implements UserModelService{

    @Autowired
    private UserModelRepository userModelRepository;


    @Override
    public UserModel findByUsername(String username) {

        return this.userModelRepository.findByUsername(username);
    }
}
