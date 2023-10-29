package com.example.BenXe.Model;

import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "DiaDiem")
public class DiaDiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaDiaDiem;

    private String DiaDiem;
    
    private Double lng;
    
    private Double lat;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "diaDiem", cascade = CascadeType.ALL)
    private List<PhieuDatVe> phieuDatVes;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "diemDi", cascade = CascadeType.ALL)
    private List<Tuyen> tuyenDiemDis;
    
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "diemDen", cascade = CascadeType.ALL)
    private List<Tuyen> tuyenDiemDens;

    public DiaDiem() {
    }

    public DiaDiem(Long MaDiaDiem, String DiaDiem, Double lng, Double lat, List<PhieuDatVe> phieuDatVes, List<Tuyen> tuyenDiemDis, List<Tuyen> tuyenDiemDens) {
        this.MaDiaDiem = MaDiaDiem;
        this.DiaDiem = DiaDiem;
        this.lng = lng;
        this.lat = lat;
        this.phieuDatVes = phieuDatVes;
        this.tuyenDiemDis = tuyenDiemDis;
        this.tuyenDiemDens = tuyenDiemDens;
    }

    public Long getMaDiaDiem() {
        return this.MaDiaDiem;
    }

    public void setMaDiaDiem(Long MaDiaDiem) {
        this.MaDiaDiem = MaDiaDiem;
    }

    public String getDiaDiem() {
        return this.DiaDiem;
    }

    public void setDiaDiem(String DiaDiem) {
        this.DiaDiem = DiaDiem;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public List<PhieuDatVe> getPhieuDatVes() {
        return this.phieuDatVes;
    }

    public void setPhieuDatVes(List<PhieuDatVe> phieuDatVes) {
        this.phieuDatVes = phieuDatVes;
    }

    public List<Tuyen> getTuyenDiemDis() {
        return this.tuyenDiemDis;
    }

    public void setTuyenDiemDis(List<Tuyen> tuyenDiemDis) {
        this.tuyenDiemDis = tuyenDiemDis;
    }

    public List<Tuyen> getTuyenDiemDens() {
        return this.tuyenDiemDens;
    }

    public void setTuyenDiemDens(List<Tuyen> tuyenDiemDens) {
        this.tuyenDiemDens = tuyenDiemDens;
    }

    public DiaDiem MaDiaDiem(Long MaDiaDiem) {
        setMaDiaDiem(MaDiaDiem);
        return this;
    }

    public DiaDiem DiaDiem(String DiaDiem) {
        setDiaDiem(DiaDiem);
        return this;
    }

    public DiaDiem lng(Double lng) {
        setLng(lng);
        return this;
    }

    public DiaDiem lat(Double lat) {
        setLat(lat);
        return this;
    }

    public DiaDiem phieuDatVes(List<PhieuDatVe> phieuDatVes) {
        setPhieuDatVes(phieuDatVes);
        return this;
    }

    public DiaDiem tuyenDiemDis(List<Tuyen> tuyenDiemDis) {
        setTuyenDiemDis(tuyenDiemDis);
        return this;
    }

    public DiaDiem tuyenDiemDens(List<Tuyen> tuyenDiemDens) {
        setTuyenDiemDens(tuyenDiemDens);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DiaDiem)) {
            return false;
        }
        DiaDiem diaDiem = (DiaDiem) o;
        return Objects.equals(MaDiaDiem, diaDiem.MaDiaDiem) && Objects.equals(DiaDiem, diaDiem.DiaDiem) && Objects.equals(lng, diaDiem.lng) && Objects.equals(lat, diaDiem.lat) && Objects.equals(phieuDatVes, diaDiem.phieuDatVes) && Objects.equals(tuyenDiemDis, diaDiem.tuyenDiemDis) && Objects.equals(tuyenDiemDens, diaDiem.tuyenDiemDens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaDiaDiem, DiaDiem, lng, lat, phieuDatVes, tuyenDiemDis, tuyenDiemDens);
    }

    @Override
    public String toString() {
        return "{" +
            " MaDiaDiem='" + getMaDiaDiem() + "'" +
            ", DiaDiem='" + getDiaDiem() + "'" +
            ", lng='" + getLng() + "'" +
            ", lat='" + getLat() + "'" +
            ", phieuDatVes='" + getPhieuDatVes() + "'" +
            ", tuyenDiemDis='" + getTuyenDiemDis() + "'" +
            ", tuyenDiemDens='" + getTuyenDiemDens() + "'" +
            "}";
    }

}
