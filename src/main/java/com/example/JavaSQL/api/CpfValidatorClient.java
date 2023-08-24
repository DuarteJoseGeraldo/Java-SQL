package com.example.JavaSQL.api;


import com.example.JavaSQL.DTOs.PersonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cpfValidator", url = "http://localhost:7090")
public interface CpfValidatorClient {
    @PostMapping(path = "/cpf")
    boolean validateCpf(@RequestBody PersonDTO data);
}
