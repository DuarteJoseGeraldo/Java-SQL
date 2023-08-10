package com.example.JavaSQL.service;

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
            return personRepo.findAll();
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
        return null;
    }
}


