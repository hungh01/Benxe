package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;

@Entity
@Table(name = "PhieuDangKyTuyen")
public class PhieuDangKyTuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PhieuDangKyTuyen;

    @Column(name = "ThoiGianBatDauVanHanh")
    private Date ThoiGianBatDauVanHanh;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaNV")
    private NhanVien nhanVien;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaCX")
    private ChuXe chuXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaTuyen")
    private Tuyen tuyen;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaLX")
    private LoaiXe loaiXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "BKS")
    private Xe xe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "giaveId")
    private GiaVe giaVe;

    public PhieuDangKyTuyen() {
    }

    public PhieuDangKyTuyen(Long phieuDangKyTuyen, Date thoiGianBatDauVanHanh, NhanVien nhanVien, ChuXe chuXe, Tuyen tuyen, LoaiXe loaiXe, Xe xe, GiaVe giaVe) {
        PhieuDangKyTuyen = phieuDangKyTuyen;
        ThoiGianBatDauVanHanh = thoiGianBatDauVanHanh;
        this.nhanVien = nhanVien;
        this.chuXe = chuXe;
        this.tuyen = tuyen;
        this.loaiXe = loaiXe;
        this.xe = xe;
        this.giaVe = giaVe;
    }

    public Long getPhieuDangKyTuyen() {
        return PhieuDangKyTuyen;
    }

    public void setPhieuDangKyTuyen(Long phieuDangKyTuyen) {
        PhieuDangKyTuyen = phieuDangKyTuyen;
    }

    public Date getThoiGianBatDauVanHanh() {
        return ThoiGianBatDauVanHanh;
    }

    public void setThoiGianBatDauVanHanh(Date thoiGianBatDauVanHanh) {
        ThoiGianBatDauVanHanh = thoiGianBatDauVanHanh;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ChuXe getChuXe() {
        return chuXe;
    }

    public void setChuXe(ChuXe chuXe) {
        this.chuXe = chuXe;
    }

    public Tuyen getTuyen() {
        return tuyen;
    }

    public void setTuyen(Tuyen tuyen) {
        this.tuyen = tuyen;
    }

    public LoaiXe getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public Xe getXe() {
        return xe;
    }

    public void setXe(Xe xe) {
        this.xe = xe;
    }

    public GiaVe getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(GiaVe giaVe) {
        this.giaVe = giaVe;
    }
}
