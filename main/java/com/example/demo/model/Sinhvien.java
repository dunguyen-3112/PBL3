package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "sinhvien")
public class Sinhvien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_sinh_vien", nullable = false)
    @SequenceGenerator(name="id",
                        sequenceName = "id",
                        allocationSize = 1,
                        initialValue=100000000)
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator="id")
    private Long maSinhVien;

    @Column(name = "cccd", nullable = false)
    private String cccd;

    @Column(name = "sđt")
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "gioi_tinh", nullable = false)
    private Integer gioiTinh;

    @Column(name = "ma_lop")
    private Long maLop;

    @Column(name = "ngay_sinh", nullable = false)
    private Date ngaySinh;

    @Column(name = "ten_sinh_vien", nullable = false)
    private String tenSinhVien;

    @Column(name = "image")
    private boolean image;
}
