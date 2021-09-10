package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "Registered")
public class Registered implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name="id",
    sequenceName = "id",
    allocationSize = 1,
    initialValue=100000000)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idBook")
    private Long idBook;

    @Column(name = "idStudent")
    private Long idStudent;

    @Column(name = "registrationDate", nullable = false)
    private Date registrationDate;

    @Column(name = "time", nullable = false)
    private int time;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @Column(name = "idUser",nullable = true)
    private Long idUser;
}
