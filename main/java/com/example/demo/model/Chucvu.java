package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "chucvu")
public class Chucvu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_chuc_vu", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChucVu;

    @Column(name = "ten_chuc_vu", nullable = false)
    private String tenChucVu;

    @Column(name = "so_luong", nullable = false)
    private Long soLuong = 0L;
}
