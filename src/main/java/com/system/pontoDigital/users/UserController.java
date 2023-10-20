package com.system.pontoDigital.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    @Autowired 
    private IUserRepository userRepository;



    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserModel userModel){
        
        var user =this.userRepository.findByEmail(userModel.getEmail());

        if(user != null){
            return ResponseEntity.status(400).body("usuario já existe");
        }


        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(200).body(userCreated);
    }
    
    @GetMapping("/getUsers")
    public List<UserModel> getUsers(){
        var users = this.userRepository.findAll();
        return users;
        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){

        var user = this.userRepository.findById(id);
        if(user!= null){
           this.userRepository.deleteById(id);;
           
        }

        return null;
        

        
    }


    
}