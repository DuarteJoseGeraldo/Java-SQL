package com.example.JavaSQL.service;

import com.example.JavaSQL.entity.AddressEntity;
import com.example.JavaSQL.DTOs.PersonDTO;
import com.example.JavaSQL.entity.PersonEntity;
import com.example.JavaSQL.repository.AddressRepository;
import com.example.JavaSQL.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {
    @Autowired
    PersonRepository personRepo;

    @Autowired
    AddressRepository addressRepo;

    public PersonEntity findPerson(Long id) throws Exception {

        Optional<PersonEntity> person = personRepo.findById(id);
        return (person.orElse(null));


    }

    public List<PersonEntity> getAll() throws Exception {
        return personRepo.findAllByIdAfter((long) 0);

    }

    public PersonEntity register(PersonDTO data) throws Exception {

        if (Objects.isNull(data)) throw new Exception("Person data is null");
        if (Objects.isNull((data.getName())) || Objects.isNull((data.getAddress())))
            throw new Exception("Person data has null content");
        PersonEntity newPerson = PersonEntity.builder().name(data.getName()).build();
        newPerson = personRepo.save(newPerson);

        AddressEntity newAddress = new AddressEntity();
        newAddress.setAddress(data.getAddress());
        newAddress.setPerson_id(newPerson.getId());
        addressRepo.save(newAddress);

        return (personRepo.findById(newPerson.getId()).orElse(null));
    }
}


