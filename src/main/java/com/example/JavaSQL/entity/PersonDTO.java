package com.example.JavaSQL.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {
    private String name;
    private String address;
}
