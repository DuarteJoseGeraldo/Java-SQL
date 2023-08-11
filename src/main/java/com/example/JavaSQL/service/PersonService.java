package com.example.JavaSQL.service;

import com.example.JavaSQL.entity.AddressEntity;
import com.example.JavaSQL.entity.PersonDTO;
import com.example.JavaSQL.entity.PersonEntity;
import com.example.JavaSQL.repository.AddressRepository;
import com.example.JavaSQL.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Component
public class PersonService {
    @Autowired
    PersonRepository personRepo;

    @Autowired
    AddressRepository addressRepo;

    public PersonEntity findPerson(Long id) {
        try {
            Optional<PersonEntity> person = personRepo.findById(id);
            return (person.orElse(null));
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
        return null;
    }

    public List<PersonEntity> getAll() {
        try {
            return personRepo.findAllByIdAfter((long) 0);
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
        return null;
    }

    public PersonEntity register(PersonDTO data) {
        try {
            PersonEntity newPerson = new PersonEntity();
            newPerson.setName(data.getName());
            newPerson = personRepo.save(newPerson);

            AddressEntity newAddress = new AddressEntity();
            newAddress.setAddress(data.getAddress());
            newAddress.setPerson_id(newPerson.getId());
            addressRepo.save(newAddress);

            return (personRepo.findById(newPerson.getId()).orElse(null));

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
        return null;
    }
}


