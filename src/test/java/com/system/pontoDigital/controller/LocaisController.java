package com.system.pontoDigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.pontoDigital.interfaces.ILocaisRepository;
import com.system.pontoDigital.model.Locais;


@RestController
@RequestMapping("/locais")
public class LocaisController {

    @Autowired
    private ILocaisRepository iLocaisRepository;


    @PostMapping("/")
    public ResponseEntity createLocal (@RequestBody Locais locais){
        var localCreated = this.iLocaisRepository.save(locais);

        return ResponseEntity.status(200).body(localCreated);
    }

    @GetMapping("/list")
    public List<Locais> locaisList(){

        var locais = this.iLocaisRepository.findAll();

        return locais;
    }
}
