package com.system.pontoDigital.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "usuarios")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    private String status;
    private String userType;
    private LocalDate birthday ;

    @Column(nullable = false, unique = true)
    private Long cpf;
    
    @Column(unique = true)
    private String matricula;

    @Column(unique = true)
    private Long phoneNumber;
    
    private Integer cargoName;
    private Integer locaisName;
    
}
