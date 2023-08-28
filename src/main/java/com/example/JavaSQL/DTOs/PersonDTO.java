package com.example.JavaSQL.DTOs;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
public class PersonDTO {
    private long id;
    private String name;
    private String cpf;
    private String address;
}
