package com.example.JavaSQL.controllers;

import com.example.JavaSQL.DTOs.PersonDTO;
import com.example.JavaSQL.entity.PersonEntity;
import com.example.JavaSQL.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/find")
    public ResponseEntity<?> findPerson(@RequestParam Long id) throws Exception {
        return ResponseEntity.ok(service.findPerson(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAll() throws Exception {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody PersonDTO data) throws Exception {
        PersonEntity newPerson = service.register(data);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(newPerson)
                .toUri();
        return ResponseEntity.created(location).body(newPerson);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PersonDTO data, @RequestParam Long id) throws Exception {
        PersonEntity updatedData = service.update(data, id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(updatedData)
                .toUri();
        return ResponseEntity.created(location).body(updatedData);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.status(200).build();
    }
}