package com.example.JavaSQL.controllers;

import com.example.JavaSQL.entity.PersonDTO;
import com.example.JavaSQL.entity.PersonEntity;
import com.example.JavaSQL.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/find")
    public ResponseEntity<PersonEntity> findPerson(@RequestParam Long id) {
        return ResponseEntity.ok(service.findPerson(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PersonEntity>> listAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody PersonDTO data) {
        PersonEntity newPerson = service.register(data);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPerson)
                .toUri();

        return ResponseEntity.created(location).body(newPerson);
    }
}