package com.system.pontoDigital.users;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name="td_user")
public class UserModel {

    @Id
    @GeneratedValue(generator="Long")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    private String picture;

    private String userType;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
