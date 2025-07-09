package com.squad21.pitang.GeneralFilter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.squad21.pitang.User.Client.ClientController.ClientRepository.ClientRepository;
import com.squad21.pitang.User.Manager.ManagerRepository.MnRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GeneralFilter extends OncePerRequestFilter {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MnRepository managerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var servletPath = request.getServletPath();
        
        if (servletPath.equals("/login")) {
            var authorization = request.getHeader("Authorization");
            System.out.println(authorization);

            var authencoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authencoded);
            var authString = new String(authDecode);
            System.out.println("Authorization");
            System.out.println(authString);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            System.out.println(username);
            System.out.println(password);

            // Check if the user is a client
                var user = clientRepository.findByCpf(username);
                if (user != null) {
                    // Validate the password
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    if (passwordVerify.verified) {
                        request.setAttribute("idUser", user.getId());
                        request.setAttribute("balance", user.getBalance());
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        response.setStatus(401);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Incorret password!\"}");
                        return;
                    }
                }
                // If the client doesn't exist it looks after the manager.
                var manager = managerRepository.findByname(username);
                if (manager == null) {  
                    response.setStatus(401);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"User not found\"}");
                } else {
                    // Validate the password of the manager
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), manager.getPassword());
                    if (passwordVerify.verified) {
                        request.setAttribute("idUser", manager.getId());
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        response.setStatus(401);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Incorret password!\"}");
                    }
                }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
