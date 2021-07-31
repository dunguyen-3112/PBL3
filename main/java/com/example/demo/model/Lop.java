package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "lop")
public class Lop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_lop", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLop;

    @Column(name = "ma_khoa")
    private Long maKhoa;

    @Column(name = "ten_lop", nullable = false)
    private String tenLop;

    @Column(name = "so_luong", nullable = false)
    private Long soLuong = 0L;
}
