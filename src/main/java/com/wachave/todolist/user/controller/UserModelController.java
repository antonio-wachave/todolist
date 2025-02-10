package com.wachave.todolist.user.controller;

import com.wachave.todolist.user.entity.UserModel;
import com.wachave.todolist.user.repository.UserModelRepository;
import com.wachave.todolist.user.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserModelController {

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private UserModelRepository userModelRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){

        UserModel user = this.userModelService.findByUsername(userModel.getUsername());

        if(user != null){
            System.out.print(" usuario ja existe! ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe!") ;
        }

        UserModel createdUser = this.userModelRepository.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }

}
