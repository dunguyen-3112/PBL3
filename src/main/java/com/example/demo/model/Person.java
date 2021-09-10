package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@MappedSuperclass
public class Person {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name="id",
                        sequenceName = "id",
                        allocationSize = 1,
                        initialValue=100000000)
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator="id")
    private Long id;

    private String name;

    private String address;

    private String code;

    private String phoneNumber;

    private String emailNumber;

    private String password;

    private boolean gender;

    private Date birthdate;
  
}
