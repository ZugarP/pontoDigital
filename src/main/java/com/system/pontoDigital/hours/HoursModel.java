package com.system.pontoDigital.hours;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name= "td_tasks")
public class HoursModel {
    
    @Id
    @GeneratedValue(generator = "Long")
    private long id;

    private LocalDate date;

    private LocalTime entry;
    private LocalTime lunch;
    private LocalTime lunchReturn;
    private LocalTime exit;

    private Long idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    
}
