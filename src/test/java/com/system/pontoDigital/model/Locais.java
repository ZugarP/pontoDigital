package com.system.pontoDigital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Locais {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    private long workers;
    private String address;

    @Column(unique = true)
    private Long phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    private Long cnpj;

    
}
