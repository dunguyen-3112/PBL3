package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "sach")
public class Sach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_sach", nullable = false)
    @SequenceGenerator(name="id",
                        sequenceName = "id",
                        allocationSize = 1,
                        initialValue=100000000)
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator="id")
    private Long maSach;

    @Column(name = "gia", nullable = false)
    private Double gia;

    @Column(name = "ma_the_loai")
    private Long maTheLoai;

    @Column(name = "ngayxb", nullable = false)
    private Date ngayxb;

    @Column(name = "nha_xuat_ban", nullable = false)
    private String nhaXuatBan;

    @Column(name = "so_luong_begin", nullable = false)
    private Long soLuongBegin;

    @Column(name = "so_luong_now", nullable = false)
    private Long soLuongNow;

    @Column(name = "sotrang", nullable = false)
    private Long sotrang;

    @Column(name = "tac_gia", nullable = false)
    private String tacGia;

    @Column(name = "ten_sach", nullable = false)
    private String tenSach;

    @Column(name = "image", nullable = false)
    private boolean image = false;

    @Column(name = "TT",nullable = false)
    private boolean TT = true;
}
