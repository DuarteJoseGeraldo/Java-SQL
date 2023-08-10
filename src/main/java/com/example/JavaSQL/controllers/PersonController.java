package com.example.JavaSQL.controllers;

import com.example.JavaSQL.entity.PersonDTO;
import com.example.JavaSQL.entity.PersonEntity;
import com.example.JavaSQL.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/people/find")
    public ResponseEntity<PersonEntity> findPerson(@RequestParam Long id) {
        return ResponseEntity.ok(service.findPerson(id));
    }

    @GetMapping("/people/list")
    public ResponseEntity<List<PersonEntity>> listAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/people")
    public ResponseEntity<?> register(@RequestBody PersonDTO data) {
        service.resgister(data);
        return ResponseEntity.created();
    }
}


