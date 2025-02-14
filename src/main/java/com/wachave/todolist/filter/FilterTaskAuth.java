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

        String servletPath = request.getServletPath();

        if(servletPath.equals("/tasks/")) {

            //catch the auth(username and password)
            String authorization = request.getHeader("Authorization");

            String authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecade = Base64.getDecoder().decode(authEncoded);

            String authString = new String(authDecade);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            //validation user
            UserModel user = this.userModelRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401);
            } else {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser",user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }
}
