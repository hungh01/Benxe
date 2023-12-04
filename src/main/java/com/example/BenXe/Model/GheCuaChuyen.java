package com.example.BenXe.Model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "GheCuaChuyen")
public class GheCuaChuyen {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GheCuaChuyen;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaChuyen")
    private ChuyenXe chuyenXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaGhe")
    private Ghe ghe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "PhieuDatVe")
    private PhieuDatVe phieuDatVe;

    @Column(name = "TrangThai")
    private Boolean TrangThai;


    public GheCuaChuyen() {
    }

    public GheCuaChuyen(Long GheCuaChuyen, ChuyenXe chuyenXe, Ghe ghe, PhieuDatVe phieuDatVe, Boolean TrangThai) {
        this.GheCuaChuyen = GheCuaChuyen;
        this.chuyenXe = chuyenXe;
        this.ghe = ghe;
        this.phieuDatVe = phieuDatVe;
        this.TrangThai = TrangThai;
    }

    public Long getGheCuaChuyen() {
        return this.GheCuaChuyen;
    }

    public void setGheCuaChuyen(Long GheCuaChuyen) {
        this.GheCuaChuyen = GheCuaChuyen;
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

    public PhieuDatVe getPhieuDatVe() {
        return this.phieuDatVe;
    }

    public void setPhieuDatVe(PhieuDatVe phieuDatVe) {
        this.phieuDatVe = phieuDatVe;
    }

    public Boolean isTrangThai() {
        return this.TrangThai;
    }

    public Boolean getTrangThai() {
        return this.TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public GheCuaChuyen GheCuaChuyen(Long GheCuaChuyen) {
        setGheCuaChuyen(GheCuaChuyen);
        return this;
    }

    public GheCuaChuyen chuyenXe(ChuyenXe chuyenXe) {
        setChuyenXe(chuyenXe);
        return this;
    }

    public GheCuaChuyen ghe(Ghe ghe) {
        setGhe(ghe);
        return this;
    }

    public GheCuaChuyen phieuDatVe(PhieuDatVe phieuDatVe) {
        setPhieuDatVe(phieuDatVe);
        return this;
    }

    public GheCuaChuyen TrangThai(Boolean TrangThai) {
        setTrangThai(TrangThai);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GheCuaChuyen)) {
            return false;
        }
        GheCuaChuyen gheCuaChuyen = (GheCuaChuyen) o;
        return Objects.equals(GheCuaChuyen, gheCuaChuyen.GheCuaChuyen) && Objects.equals(chuyenXe, gheCuaChuyen.chuyenXe) && Objects.equals(ghe, gheCuaChuyen.ghe) && Objects.equals(phieuDatVe, gheCuaChuyen.phieuDatVe) && Objects.equals(TrangThai, gheCuaChuyen.TrangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(GheCuaChuyen, chuyenXe, ghe, phieuDatVe, TrangThai);
    }

    @Override
    public String toString() {
        return "{" +
            " GheCuaChuyen='" + getGheCuaChuyen() + "'" +
            ", chuyenXe='" + getChuyenXe() + "'" +
            ", ghe='" + getGhe() + "'" +
            ", phieuDatVe='" + getPhieuDatVe() + "'" +
            ", TrangThai='" + isTrangThai() + "'" +
            "}";
    }
    
    
}
