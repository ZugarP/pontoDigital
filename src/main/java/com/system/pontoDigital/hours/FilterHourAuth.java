package com.system.pontoDigital.hours;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.system.pontoDigital.users.IUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterHourAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

              var servletPath = request.getServletPath();
                if(servletPath.startsWith("/hours/")){

                  // Pegar autenticação usuario e senha  
                      // recebe o request
                  var authorization = request.getHeader("Authorization");

                  
                      // limpa o request para ficar somente a parte necessaria
                  var authEncoded = authorization.substring("Basic".length()).trim();
       

                  // converte em bytes
                  byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
                      // converte o byte em string
                  var authString = new String(authDecoded); 
                      // Divide a string em usuario e senha 
                  String[] credentials = authString.split(":");
                  String email = credentials[0];
                  String password = credentials[1];


                  // validar usuario e senha

                  var user = this.userRepository.findByEmail(email);
                  if( user == null){
                      response.sendError(401);
                  }else{
                    if(password.equals(user.getPassword())){
                      request.setAttribute("idUser",user.getId());
                      filterChain.doFilter(request, response);
                    }else{
                      response.sendError(401);
                    }

                  }
                }else{

                filterChain.doFilter(request, response);
              
              }


    }
    

 
    
}