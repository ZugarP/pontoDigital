package com.system.pontoDigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.pontoDigital.interfaces.ICargosRepository;
import com.system.pontoDigital.model.Cargos;


@RestController
@RequestMapping("/cargos")
public class CargosController {

    @Autowired
    private ICargosRepository iCargosRepository;


    @PostMapping("/")
    public ResponseEntity<Cargos> createCargo (@RequestBody Cargos cargos){
        var cargoCreated = this.iCargosRepository.save(cargos);

        return ResponseEntity.status(200).body(cargoCreated);
    }

    @GetMapping("/list")
    public List<Cargos> cargosList(){
        var cargos = this.iCargosRepository.findAll();

        return cargos;
    }
    
}
