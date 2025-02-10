package com.wachave.todolist.user.service;

import com.wachave.todolist.user.entity.UserModel;

public interface UserModelService  {

    UserModel findByUsername(String username);


}
