package com.system.pontoDigital.users;

import org.springframework.data.jpa.repository.JpaRepository;



public interface IUserRepository extends JpaRepository<UserModel,Long>{

    UserModel findByEmail(String email);

    
}