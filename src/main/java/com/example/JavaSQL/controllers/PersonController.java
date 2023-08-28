package com.example.JavaSQL.controllers;

import com.example.JavaSQL.DTOs.AddressDTO;
import com.example.JavaSQL.DTOs.PersonDTO;
import com.example.JavaSQL.entity.AddressEntity;
import com.example.JavaSQL.entity.PersonEntity;
import com.example.JavaSQL.service.AddressService;
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

    private final PersonService personService;

    private final AddressService addressService;

    @Autowired
    public PersonController(PersonService personService, AddressService addressService) {
        this.personService = personService;
        this.addressService = addressService;
    }

    @GetMapping("/find")
    public ResponseEntity<?> findPerson(@RequestParam Long id) throws Exception {
        return ResponseEntity.ok(personService.findPersonCached(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAll() throws Exception {
        return ResponseEntity.ok(personService.getAll());
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody PersonDTO data) throws Exception {
        PersonEntity newPerson = personService.register(data);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(newPerson)
                .toUri();
        return ResponseEntity.created(location).body(newPerson);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PersonDTO data, @RequestParam Long id) throws Exception {
        PersonEntity updatedData = personService.update(data, id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(updatedData)
                .toUri();
        return ResponseEntity.created(location).body(updatedData);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) throws Exception {
        personService.delete(id);
        return ResponseEntity.status(200).build();
    }

    //    teste para editar um endereço especifico sem passar pela pessoa
    @PutMapping("/address")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDTO newData, @RequestParam Long id) throws Exception {
        AddressEntity updatedData = addressService.update(id, newData.getAddress());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(updatedData)
                .toUri();

        return ResponseEntity.created(location).body(updatedData);

    }

}