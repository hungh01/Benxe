package com.example.BenXe.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    @Column(name = "TinhTrangVe")
    private String TinhTrangVe;

    @Column(name = "NgayDat")
    private LocalDate NgayDat;

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
    @JoinColumn(name = "MaChuyenXe")
    private ChuyenXe chuyenXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaGhe")
    private Ghe ghe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaKH")
    private KhachHang khachHang;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "ViTriLenXe")
    private DiaDiem diaDiem;


    public PhieuDatVe() {
    }

    public PhieuDatVe(Long MaPhieuDatVe, String TinhTrangVe, LocalDate NgayDat, String GhiChu, String DanhGiaChuyenXe, NhanVien nhanVien, ChuyenXe chuyenXe, Ghe ghe, KhachHang khachHang, DiaDiem diaDiem) {
        this.MaPhieuDatVe = MaPhieuDatVe;
        this.TinhTrangVe = TinhTrangVe;
        this.NgayDat = NgayDat;
        this.GhiChu = GhiChu;
        this.DanhGiaChuyenXe = DanhGiaChuyenXe;
        this.nhanVien = nhanVien;
        this.chuyenXe = chuyenXe;
        this.ghe = ghe;
        this.khachHang = khachHang;
        this.diaDiem = diaDiem;
    }

    public Long getMaPhieuDatVe() {
        return this.MaPhieuDatVe;
    }

    public void setMaPhieuDatVe(Long MaPhieuDatVe) {
        this.MaPhieuDatVe = MaPhieuDatVe;
    }

    public String getTinhTrangVe() {
        return this.TinhTrangVe;
    }

    public void setTinhTrangVe(String TinhTrangVe) {
        this.TinhTrangVe = TinhTrangVe;
    }

    public LocalDate getNgayDat() {
        return this.NgayDat;
    }

    public void setNgayDat(LocalDate NgayDat) {
        this.NgayDat = NgayDat;
    }

    public String getGhiChu() {
        return this.GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getDanhGiaChuyenXe() {
        return this.DanhGiaChuyenXe;
    }

    public void setDanhGiaChuyenXe(String DanhGiaChuyenXe) {
        this.DanhGiaChuyenXe = DanhGiaChuyenXe;
    }

    public NhanVien getNhanVien() {
        return this.nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ChuyenXe getChuyenXe() {
        return this.chuyenXe;
    }

    public void setChuyenXe(ChuyenXe chuyenXe) {
        this.chuyenXe = chuyenXe;
    }

    public Ghe getGhe() {
        return this.ghe;
    }

    public void setGhe(Ghe ghe) {
        this.ghe = ghe;
    }

    public KhachHang getKhachHang() {
        return this.khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public DiaDiem getDiaDiem() {
        return this.diaDiem;
    }

    public void setDiaDiem(DiaDiem diaDiem) {
        this.diaDiem = diaDiem;
    }

    public PhieuDatVe MaPhieuDatVe(Long MaPhieuDatVe) {
        setMaPhieuDatVe(MaPhieuDatVe);
        return this;
    }

    public PhieuDatVe TinhTrangVe(String TinhTrangVe) {
        setTinhTrangVe(TinhTrangVe);
        return this;
    }

    public PhieuDatVe NgayDat(LocalDate NgayDat) {
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

    public PhieuDatVe chuyenXe(ChuyenXe chuyenXe) {
        setChuyenXe(chuyenXe);
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

    public PhieuDatVe diaDiem(DiaDiem diaDiem) {
        setDiaDiem(diaDiem);
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
        return Objects.equals(MaPhieuDatVe, phieuDatVe.MaPhieuDatVe) && Objects.equals(TinhTrangVe, phieuDatVe.TinhTrangVe) && Objects.equals(NgayDat, phieuDatVe.NgayDat) && Objects.equals(GhiChu, phieuDatVe.GhiChu) && Objects.equals(DanhGiaChuyenXe, phieuDatVe.DanhGiaChuyenXe) && Objects.equals(nhanVien, phieuDatVe.nhanVien) && Objects.equals(chuyenXe, phieuDatVe.chuyenXe) && Objects.equals(ghe, phieuDatVe.ghe) && Objects.equals(khachHang, phieuDatVe.khachHang) && Objects.equals(diaDiem, phieuDatVe.diaDiem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaPhieuDatVe, TinhTrangVe, NgayDat, GhiChu, DanhGiaChuyenXe, nhanVien, chuyenXe, ghe, khachHang, diaDiem);
    }

    @Override
    public String toString() {
        return "{" +
            " MaPhieuDatVe='" + getMaPhieuDatVe() + "'" +
            ", TinhTrangVe='" + getTinhTrangVe() + "'" +
            ", NgayDat='" + getNgayDat() + "'" +
            ", GhiChu='" + getGhiChu() + "'" +
            ", DanhGiaChuyenXe='" + getDanhGiaChuyenXe() + "'" +
            ", nhanVien='" + getNhanVien() + "'" +
            ", chuyenXe='" + getChuyenXe() + "'" +
            ", ghe='" + getGhe() + "'" +
            ", khachHang='" + getKhachHang() + "'" +
            ", diaDiem='" + getDiaDiem() + "'" +
            "}";
    }
    
}