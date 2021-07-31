package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "nhanvien")
public class Nhanvien implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "manv", nullable = false)
    @SequenceGenerator(name="id",
                        sequenceName = "id",
                        allocationSize = 1,
                        initialValue=100000000)
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator="id")
    private Long maNV;

    @Column(name = "cccd", nullable = false)
    private String cccd;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "ma_chuc_vu")
    private Long maChucVu;

    @Column(name = "ngay_sinh", nullable = false)
    private Date ngaySinh;

    @Column(name = "phone")
    private String phone;

    @Column(name = "tennv", nullable = false)
    private String tenNV;

    @Column(name = "image", nullable = false)
    private Boolean image = false;

    @Column(name = "password")
    private String password; 

}
