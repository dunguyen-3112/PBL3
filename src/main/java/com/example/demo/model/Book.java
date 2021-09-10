package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name="id",
                        sequenceName = "id",
                        allocationSize = 1,
                        initialValue=100000000)
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator="id")
    private Long id;

    @Column(name = "sale", nullable = false)
    private Double sale;

    @Column(name = "idgenre")
    private Long idgenre;

    @Column(name = "releaDate", nullable = false)
    private Date releaDate;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "pages", nullable = false)
    private Long pages;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status",nullable = false)
    private boolean status = true;

    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "idUser",nullable = true)
    private Long idUser;

}
