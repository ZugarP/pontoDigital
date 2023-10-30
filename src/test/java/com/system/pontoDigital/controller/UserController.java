package com.system.pontoDigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.pontoDigital.interfaces.IUserRepository;
import com.system.pontoDigital.model.User;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){

        var userCreated = this.iUserRepository.save(user);
        return ResponseEntity.status(200).body(userCreated);
    }

    @GetMapping("/list")
    public List<User> userList(){
        var userList = this.iUserRepository.findAll();

        return userList;
    }

    @GetMapping("/{id}")
    public User getUser(HttpServletRequest request ,@RequestBody User user, @PathVariable("id")Long id){
        var idUser = request.getAttribute("id");
        var userType = request.getAttribute("userType");

        var userInf = this.iUserRepository.findById(id).orElse(null);

        if (idUser.equals(user.getId()) || userType.equals("administrador")) {
            return userInf;
        }


        return null;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(HttpServletRequest request, @RequestBody User user, @PathVariable("id")Long id){
        var idUser = request.getAttribute("id");
        var userType = request.getAttribute("userType");

        var userInf = this.iUserRepository.findById(id).orElse(null);

        if (idUser.equals(user.getId()) || userType.equals("administrador")) {
                        
            // TODO  criar verificação para cada um se nao estiver funcionando corretamente 

            // var userBd = user.getBirthday().equals(userInf.getBirthday());
            
            // var userCN = user.getCargoName().equals(userInf.getCargoName());
            
            // var userCPF = user.getCpf().equals(userInf.getCpf());

            // var userE = user.getEmail().equals(userInf.getEmail());
    
            // var userFN = user.getFirstName().equals(userInf.getFirstName());
            
            // var userLN = user.getLastName().equals(userInf.getLastName());
            
            // var userLocal = user.getLocaisName().equals(userInf.getLocaisName());
            
            // var userM = user.getMatricula().equals(userInf.getMatricula());
            
            // var userP = user.getPhoneNumber().equals(userInf.getPhoneNumber());
            
            // var userS= user.getStatus().equals(userInf.getStatus());
            
            // var userT = user.getUserType().equals(userInf.getUserType());
            
            this.iUserRepository.save(user);
            return ResponseEntity.status(200).body(userInf);
        }




        return null;

    }

    
}
