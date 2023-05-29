package com.example.BenXe.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "PhieuDatVe")
public class PhieuDatVe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaPhieuDatVe;

    @Column(name = "ViTriLenXe")
    private String ViTriLenXe;

    @Column(name = "TinhTrangVe")
    private Long TinhTrangVe;

    @Column(name = "GhiChu")
    private String GhiChu;

    @Column(name = "DanhGiaChuyenXe")
    private String DanhGiaChuyenXe;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "MaGhe")
    private Ghe ghe;

    @ManyToOne
    @JoinColumn(name = "MaKH")
    private KhachHang khachHang;

    public PhieuDatVe(Long maPhieuDatVe, String viTriLenXe, Long tinhTrangVe, String ghiChu, String danhGiaChuyenXe, NhanVien nhanVien, Ghe ghe, KhachHang khachHang) {
        MaPhieuDatVe = maPhieuDatVe;
        ViTriLenXe = viTriLenXe;
        TinhTrangVe = tinhTrangVe;
        GhiChu = ghiChu;
        DanhGiaChuyenXe = danhGiaChuyenXe;
        this.nhanVien = nhanVien;
        this.ghe = ghe;
        this.khachHang = khachHang;
    }

    public PhieuDatVe() {
    }

    public Long getMaPhieuDatVe() {
        return MaPhieuDatVe;
    }

    public void setMaPhieuDatVe(Long maPhieuDatVe) {
        MaPhieuDatVe = maPhieuDatVe;
    }

    public String getViTriLenXe() {
        return ViTriLenXe;
    }

    public void setViTriLenXe(String viTriLenXe) {
        ViTriLenXe = viTriLenXe;
    }

    public Long getTinhTrangVe() {
        return TinhTrangVe;
    }

    public void setTinhTrangVe(Long tinhTrangVe) {
        TinhTrangVe = tinhTrangVe;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getDanhGiaChuyenXe() {
        return DanhGiaChuyenXe;
    }

    public void setDanhGiaChuyenXe(String danhGiaChuyenXe) {
        DanhGiaChuyenXe = danhGiaChuyenXe;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Ghe getGhe() {
        return ghe;
    }

    public void setGhe(Ghe ghe) {
        this.ghe = ghe;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
