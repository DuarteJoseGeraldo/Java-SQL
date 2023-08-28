package com.example.JavaSQL.service;

import com.example.JavaSQL.api.CpfValidatorClient;
import com.example.JavaSQL.entity.AddressEntity;
import com.example.JavaSQL.DTOs.PersonDTO;
import com.example.JavaSQL.entity.PersonEntity;
import com.example.JavaSQL.repository.AddressRepository;
import com.example.JavaSQL.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    CpfValidatorClient validatorClient;

    private PersonDTO findPerson(Long id) throws Exception {

        Optional<PersonEntity> person = personRepo.findById(id);
        if (person.isPresent()) {

            return PersonDTO.builder()
                    .id(person.get().getId())
                    .name(person.get().getName())
                    .cpf(person.get().getCpf())
                    .address(person.get().getAddress().get(0).getAddress())
                    .build();
        }
        throw new Exception("Can not find a person");
    }

    public List<PersonEntity> getAll() throws Exception {
        return personRepo.findAllByIdAfter((long) 0);

    }

    @Cacheable(value = "person", key = "#id")
    public PersonDTO findPersonCached(long id) throws Exception {
        return findPerson(id);
    }

    public PersonEntity register(PersonDTO data) throws Exception {

        if (Objects.isNull(data)) throw new Exception("Person data is null");
        if (Objects.isNull((data.getName()))) throw new Exception("Person name is required");
        if (Objects.isNull((data.getAddress()))) throw new Exception("Person Address is required");
        if (Objects.isNull(data.getCpf())) throw new Exception("Cpf is required");
        if (!StringUtils.hasLength(data.getName())) throw new Exception("Name field is empty");
        if (!StringUtils.hasLength(data.getAddress())) throw new Exception("Address field is empty");
        if (!StringUtils.hasLength(data.getCpf())) throw new Exception("CPF field is empty");

        if (validatorClient.validateCpf(data)) {
            PersonEntity newPerson = new PersonEntity();
            newPerson.setName(data.getName());
            newPerson.setCpf(data.getCpf());
            newPerson = personRepo.save(newPerson);

            AddressEntity newAddress = new AddressEntity();
            newAddress.setAddress(data.getAddress());
            newAddress.setPerson_id(newPerson.getId());
            addressRepo.save(newAddress);

            return (personRepo.findById(newPerson.getId()).orElse(null));
        }
        throw new Exception("Invalid CPF");

    }

    public PersonEntity update(PersonDTO data, long id) throws Exception {
        Optional<PersonEntity> person = personRepo.findById(id);

        if (person.isPresent()) {

            PersonEntity newData = person.get();

            if (Objects.nonNull(data.getName())) {
                if (StringUtils.hasLength(data.getName())) {
                    newData.setName(data.getName());
                } else throw new Exception("name is empty");

            }

            if (Objects.nonNull(data.getAddress())) {
                if (StringUtils.hasLength(data.getAddress())) {
                    AddressEntity newAddress = newData.getAddress().get(0);
                    newAddress.setAddress(data.getAddress());
                    newData.setAddress(List.of(newAddress));
                } else throw new Exception("address is empty");
            }
            return personRepo.save(newData);
        }
        throw new Exception("Can not find a person");
    }

    public void delete(long id) throws Exception {
        personRepo.deleteById(id);
    }
}