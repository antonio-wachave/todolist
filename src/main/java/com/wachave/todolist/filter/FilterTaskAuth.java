package com.wachave.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.wachave.todolist.user.entity.UserModel;
import com.wachave.todolist.user.repository.UserModelRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserModelRepository userModelRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //catch the auth(username and password)
        var authorization = request.getHeader("Authorization");

        var authEncoded = authorization.substring("Basic".length()).trim();

        byte[] authDecade = Base64.getDecoder().decode(authEncoded);

        String authString = new String(authDecade);

        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];

        System.out.println("Authorization");
        System.out.println(username);
        System.out.println(password);

        //validation user
        UserModel user = this.userModelRepository.findByUsername(username);

        if(user == null){
            response.sendError(401);
        }else{
            BCrypt.Result passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if(passwordVerify.verified){
                filterChain.doFilter(request, response);
            }else{
                response.sendError(401);
            }


        }
    }

}
