package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "student")
public class Student extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idClass;
}
