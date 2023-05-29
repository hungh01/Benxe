package com.example.BenXe.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Ghe")
public class Ghe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ghe;

    @Column(name = "MoTa")
    private String MoTa;

    @Column(name = "TrangThai")
    private Boolean TrangThai;

    @ManyToOne
    @JoinColumn(name = "MaChuyenXe")
    private ChuyenXe chuyenXe;

    @OneToMany(mappedBy = "ghe", cascade = CascadeType.ALL)
    private List<PhieuDatVe> phieuDatVes;

    public Ghe() {
    }

    public Ghe(Long ghe, String moTa, Boolean trangThai, ChuyenXe chuyenXe, List<PhieuDatVe> phieuDatVes) {
        Ghe = ghe;
        MoTa = moTa;
        TrangThai = trangThai;
        this.chuyenXe = chuyenXe;
        this.phieuDatVes = phieuDatVes;
    }

    public Long getGhe() {
        return Ghe;
    }

    public void setGhe(Long ghe) {
        Ghe = ghe;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        TrangThai = trangThai;
    }

    public ChuyenXe getChuyenXe() {
        return chuyenXe;
    }

    public void setChuyenXe(ChuyenXe chuyenXe) {
        this.chuyenXe = chuyenXe;
    }

    public List<PhieuDatVe> getPhieuDatVes() {
        return phieuDatVes;
    }

    public void setPhieuDatVes(List<PhieuDatVe> phieuDatVes) {
        this.phieuDatVes = phieuDatVes;
    }
}
