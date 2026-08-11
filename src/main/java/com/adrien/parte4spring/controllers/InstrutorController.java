package com.adrien.parte4spring.controllers;

import com.adrien.parte4spring.entities.Instrutor;
import com.adrien.parte4spring.repositories.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorRepository repository;

    @GetMapping
    public List<Instrutor> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Instrutor salvar(@RequestBody Instrutor instrutor) {
        return repository.save(instrutor);
    }

}