package com.example.JavaSQL.service;

import com.example.JavaSQL.entity.AddressEntity;
import com.example.JavaSQL.entity.PersonDTO;
import com.example.JavaSQL.entity.PersonEntity;
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

    public PersonEntity findPerson(Long id) {
        try {
            Optional<PersonEntity> person = personRepo.findById(id);
            System.out.println(person);

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

    public PersonEntity resgister(PersonDTO data) {
        try {
            PersonEntity newPerson = new PersonEntity();
            AddressEntity newAddress = new AddressEntity();
            newPerson.setName(data.getName());
            newAddress.setAddress(data.getAddress());

            return personRepo.save(newPerson);

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }
}


