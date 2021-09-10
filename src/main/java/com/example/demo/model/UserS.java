package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class UserS extends Person implements  Serializable{

    private static final long serialVersionUID = 1L;
    private Long idRole;
}
