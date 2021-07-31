package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "khoa")
public class Khoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_khoa", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhoa;

    @Column(name = "ten_khoa", nullable = false)
    private String tenKhoa;    

    @Column(name = "so_luong", nullable = false)
    private Long soLuong = 0L;
}
