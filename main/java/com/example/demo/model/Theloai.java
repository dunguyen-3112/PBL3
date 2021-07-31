package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "theloai")
public class Theloai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_the_loai", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTheLoai;

    @Column(name = "ten_the_loai", nullable = false)
    private String tenTheLoai;

    @Column(name = "so_luong", nullable = false)
    private Long soLuong = 0L;
}
