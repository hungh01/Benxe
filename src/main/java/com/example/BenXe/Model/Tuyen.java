package com.example.BenXe.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Tuyen")
public class Tuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaTuyen;

    @Column(name = "DiemDi")
    private String DiemDi;
    @Column(name = "DiemDen")
    private String DiemDen;
    @Column(name = "ThoiGianXuatBen")
    private String ThoiGianXuatBen;
    @Column(name = "ThoiGianVeBen")
    private String ThoiGianVeBen;
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes;
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<GiaVe> giaVes;

    public Tuyen(Long maTuyen, String diemDi, String diemDen, String thoiGianXuatBen, String thoiGianVeBen, List<ChuyenXe> chuyenXes, List<PhieuDangKyTuyen> phieuDangKyTuyens, List<GiaVe> giaVes) {
        MaTuyen = maTuyen;
        DiemDi = diemDi;
        DiemDen = diemDen;
        ThoiGianXuatBen = thoiGianXuatBen;
        ThoiGianVeBen = thoiGianVeBen;
        this.chuyenXes = chuyenXes;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
        this.giaVes = giaVes;
    }

    public Tuyen() {
    }

    public Long getMaTuyen() {
        return MaTuyen;
    }

    public void setMaTuyen(Long maTuyen) {
        MaTuyen = maTuyen;
    }

    public String getDiemDi() {
        return DiemDi;
    }

    public void setDiemDi(String diemDi) {
        DiemDi = diemDi;
    }

    public String getDiemDen() {
        return DiemDen;
    }

    public void setDiemDen(String diemDen) {
        DiemDen = diemDen;
    }

    public String getThoiGianXuatBen() {
        return ThoiGianXuatBen;
    }

    public void setThoiGianXuatBen(String thoiGianXuatBen) {
        ThoiGianXuatBen = thoiGianXuatBen;
    }

    public String getThoiGianVeBen() {
        return ThoiGianVeBen;
    }

    public void setThoiGianVeBen(String thoiGianVeBen) {
        ThoiGianVeBen = thoiGianVeBen;
    }

    public List<ChuyenXe> getChuyenXes() {
        return chuyenXes;
    }

    public void setChuyenXes(List<ChuyenXe> chuyenXes) {
        this.chuyenXes = chuyenXes;
    }

    public List<PhieuDangKyTuyen> getPhieuDangKyTuyens() {
        return phieuDangKyTuyens;
    }

    public void setPhieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public List<GiaVe> getGiaVes() {
        return giaVes;
    }

    public void setGiaVes(List<GiaVe> giaVes) {
        this.giaVes = giaVes;
    }
}
