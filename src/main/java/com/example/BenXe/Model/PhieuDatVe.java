package com.example.BenXe.Model;

import jakarta.persistence.*;

import java.util.Date;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.Objects;

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

    @Column(name = "NgayDat")
    private Date NgayDat;

    @Column(name = "GhiChu")
    private String GhiChu;

    @Column(name = "DanhGiaChuyenXe")
    private String DanhGiaChuyenXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaGhe")
    private Ghe ghe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaKH")
    private KhachHang khachHang;


    public PhieuDatVe(Long MaPhieuDatVe, String ViTriLenXe, Long TinhTrangVe, Date NgayDat, String GhiChu, String DanhGiaChuyenXe, NhanVien nhanVien, Ghe ghe, KhachHang khachHang) {
        this.MaPhieuDatVe = MaPhieuDatVe;
        this.ViTriLenXe = ViTriLenXe;
        this.TinhTrangVe = TinhTrangVe;
        this.NgayDat = NgayDat;
        this.GhiChu = GhiChu;
        this.DanhGiaChuyenXe = DanhGiaChuyenXe;
        this.nhanVien = nhanVien;
        this.ghe = ghe;
        this.khachHang = khachHang;
    }

    public Date getNgayDat() {
        return this.NgayDat;
    }

    public void setNgayDat(Date NgayDat) {
        this.NgayDat = NgayDat;
    }

    public PhieuDatVe MaPhieuDatVe(Long MaPhieuDatVe) {
        setMaPhieuDatVe(MaPhieuDatVe);
        return this;
    }

    public PhieuDatVe ViTriLenXe(String ViTriLenXe) {
        setViTriLenXe(ViTriLenXe);
        return this;
    }

    public PhieuDatVe TinhTrangVe(Long TinhTrangVe) {
        setTinhTrangVe(TinhTrangVe);
        return this;
    }

    public PhieuDatVe NgayDat(Date NgayDat) {
        setNgayDat(NgayDat);
        return this;
    }

    public PhieuDatVe GhiChu(String GhiChu) {
        setGhiChu(GhiChu);
        return this;
    }

    public PhieuDatVe DanhGiaChuyenXe(String DanhGiaChuyenXe) {
        setDanhGiaChuyenXe(DanhGiaChuyenXe);
        return this;
    }

    public PhieuDatVe nhanVien(NhanVien nhanVien) {
        setNhanVien(nhanVien);
        return this;
    }

    public PhieuDatVe ghe(Ghe ghe) {
        setGhe(ghe);
        return this;
    }

    public PhieuDatVe khachHang(KhachHang khachHang) {
        setKhachHang(khachHang);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhieuDatVe)) {
            return false;
        }
        PhieuDatVe phieuDatVe = (PhieuDatVe) o;
        return Objects.equals(MaPhieuDatVe, phieuDatVe.MaPhieuDatVe) && Objects.equals(ViTriLenXe, phieuDatVe.ViTriLenXe) && Objects.equals(TinhTrangVe, phieuDatVe.TinhTrangVe) && Objects.equals(NgayDat, phieuDatVe.NgayDat) && Objects.equals(GhiChu, phieuDatVe.GhiChu) && Objects.equals(DanhGiaChuyenXe, phieuDatVe.DanhGiaChuyenXe) && Objects.equals(nhanVien, phieuDatVe.nhanVien) && Objects.equals(ghe, phieuDatVe.ghe) && Objects.equals(khachHang, phieuDatVe.khachHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaPhieuDatVe, ViTriLenXe, TinhTrangVe, NgayDat, GhiChu, DanhGiaChuyenXe, nhanVien, ghe, khachHang);
    }

    @Override
    public String toString() {
        return "{" +
            " MaPhieuDatVe='" + getMaPhieuDatVe() + "'" +
            ", ViTriLenXe='" + getViTriLenXe() + "'" +
            ", TinhTrangVe='" + getTinhTrangVe() + "'" +
            ", NgayDat='" + getNgayDat() + "'" +
            ", GhiChu='" + getGhiChu() + "'" +
            ", DanhGiaChuyenXe='" + getDanhGiaChuyenXe() + "'" +
            ", nhanVien='" + getNhanVien() + "'" +
            ", ghe='" + getGhe() + "'" +
            ", khachHang='" + getKhachHang() + "'" +
            "}";
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
