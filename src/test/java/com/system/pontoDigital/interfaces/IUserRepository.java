package com.system.pontoDigital.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.pontoDigital.model.User;
import java.util.List;


public interface IUserRepository  extends JpaRepository<User, Long>{
    List<User> findByCpf(long cpf);
    
}
