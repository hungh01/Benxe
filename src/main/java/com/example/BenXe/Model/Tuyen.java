package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import java.time.LocalTime;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Tuyen")
public class Tuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaTuyen;

    @Column(name = "ThoiGianXuatBen")
    private LocalTime ThoiGianXuatBen;
    @Column(name = "ThoiGianVeBen")
    private LocalTime ThoiGianVeBen;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<GiaVe> giaVes;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "DiemDi")
    private DiaDiem diemDi;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "DiemDen")
    private DiaDiem diemDen;

    public Tuyen() {
    }

    public Tuyen(Long MaTuyen, LocalTime ThoiGianXuatBen, LocalTime ThoiGianVeBen, List<ChuyenXe> chuyenXes, List<PhieuDangKyTuyen> phieuDangKyTuyens, List<GiaVe> giaVes, DiaDiem diemDi, DiaDiem diemDen) {
        this.MaTuyen = MaTuyen;
        this.ThoiGianXuatBen = ThoiGianXuatBen;
        this.ThoiGianVeBen = ThoiGianVeBen;
        this.chuyenXes = chuyenXes;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
        this.giaVes = giaVes;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
    }

    public Long getMaTuyen() {
        return this.MaTuyen;
    }

    public void setMaTuyen(Long MaTuyen) {
        this.MaTuyen = MaTuyen;
    }

    public LocalTime getThoiGianXuatBen() {
        return this.ThoiGianXuatBen;
    }

    public void setThoiGianXuatBen(LocalTime ThoiGianXuatBen) {
        this.ThoiGianXuatBen = ThoiGianXuatBen;
    }

    public LocalTime getThoiGianVeBen() {
        return this.ThoiGianVeBen;
    }

    public void setThoiGianVeBen(LocalTime ThoiGianVeBen) {
        this.ThoiGianVeBen = ThoiGianVeBen;
    }

    public List<ChuyenXe> getChuyenXes() {
        return this.chuyenXes;
    }

    public void setChuyenXes(List<ChuyenXe> chuyenXes) {
        this.chuyenXes = chuyenXes;
    }

    public List<PhieuDangKyTuyen> getPhieuDangKyTuyens() {
        return this.phieuDangKyTuyens;
    }

    public void setPhieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public List<GiaVe> getGiaVes() {
        return this.giaVes;
    }

    public void setGiaVes(List<GiaVe> giaVes) {
        this.giaVes = giaVes;
    }

    public DiaDiem getDiemDi() {
        return this.diemDi;
    }

    public void setDiemDi(DiaDiem diemDi) {
        this.diemDi = diemDi;
    }

    public DiaDiem getDiemDen() {
        return this.diemDen;
    }

    public void setDiemDen(DiaDiem diemDen) {
        this.diemDen = diemDen;
    }

    public Tuyen MaTuyen(Long MaTuyen) {
        setMaTuyen(MaTuyen);
        return this;
    }

    public Tuyen ThoiGianXuatBen(LocalTime ThoiGianXuatBen) {
        setThoiGianXuatBen(ThoiGianXuatBen);
        return this;
    }

    public Tuyen ThoiGianVeBen(LocalTime ThoiGianVeBen) {
        setThoiGianVeBen(ThoiGianVeBen);
        return this;
    }

    public Tuyen chuyenXes(List<ChuyenXe> chuyenXes) {
        setChuyenXes(chuyenXes);
        return this;
    }

    public Tuyen phieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        setPhieuDangKyTuyens(phieuDangKyTuyens);
        return this;
    }

    public Tuyen giaVes(List<GiaVe> giaVes) {
        setGiaVes(giaVes);
        return this;
    }

    public Tuyen diemDi(DiaDiem diemDi) {
        setDiemDi(diemDi);
        return this;
    }

    public Tuyen diemDen(DiaDiem diemDen) {
        setDiemDen(diemDen);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tuyen)) {
            return false;
        }
        Tuyen tuyen = (Tuyen) o;
        return Objects.equals(MaTuyen, tuyen.MaTuyen) && Objects.equals(ThoiGianXuatBen, tuyen.ThoiGianXuatBen) && Objects.equals(ThoiGianVeBen, tuyen.ThoiGianVeBen) && Objects.equals(chuyenXes, tuyen.chuyenXes) && Objects.equals(phieuDangKyTuyens, tuyen.phieuDangKyTuyens) && Objects.equals(giaVes, tuyen.giaVes) && Objects.equals(diemDi, tuyen.diemDi) && Objects.equals(diemDen, tuyen.diemDen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaTuyen, ThoiGianXuatBen, ThoiGianVeBen, chuyenXes, phieuDangKyTuyens, giaVes, diemDi, diemDen);
    }

    @Override
    public String toString() {
        return "{" +
            " MaTuyen='" + getMaTuyen() + "'" +
            ", ThoiGianXuatBen='" + getThoiGianXuatBen() + "'" +
            ", ThoiGianVeBen='" + getThoiGianVeBen() + "'" +
            ", chuyenXes='" + getChuyenXes() + "'" +
            ", phieuDangKyTuyens='" + getPhieuDangKyTuyens() + "'" +
            ", giaVes='" + getGiaVes() + "'" +
            ", diemDi='" + getDiemDi() + "'" +
            ", diemDen='" + getDiemDen() + "'" +
            "}";
    }

    
}
