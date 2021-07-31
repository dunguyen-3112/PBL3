package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "dkmuontra")
public class Dkmuontra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "madk", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long madk;

    @Column(name = "manv")
    private Long manv;

    @Column(name = "ma_sach")
    private Long maSach;

    @Column(name = "ma_sinh_vien")
    private Long maSinhVien;

    @Column(name = "ngay_muon", nullable = false)
    private Date ngayMuon;

    @Column(name = "ngay_tra", nullable = false)
    private Date ngayTra;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai = 1;
}
