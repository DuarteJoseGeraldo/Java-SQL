package com.example.JavaSQL.controllers;

import com.example.JavaSQL.entity.PersonEntity;
import com.example.JavaSQL.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/find")
    public String findPerson(@RequestParam Long id) {
        return service.findPerson(id).getName();
    }

    @GetMapping("/list")
    public List<PersonEntity> findAll() {
        return service.getAll();
    }
}


