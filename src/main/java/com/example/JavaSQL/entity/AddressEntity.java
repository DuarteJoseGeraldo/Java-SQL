package com.example.JavaSQL.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "address")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = -3695261560195325557L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "person_id")
    private long person_id;

}