package com.example.JavaSQL.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {
    private String name;
    private String cpf;
    private String address;
}
