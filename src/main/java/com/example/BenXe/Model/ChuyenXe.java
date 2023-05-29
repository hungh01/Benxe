package com.example.BenXe.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ChuyenXe")
public class ChuyenXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaChuyenXe;

    @Column(name = "SoViTriConTrong")
    private Long SoViTriConTrong;

    @Column(name = "GiaTriChuyenXe")
    private Double GiaTriChuyenXe;

    @Column(name = "NgayChay")
    private Date NgayChay;

    @ManyToOne
    @JoinColumn(name = "BKS")
    private Xe xe;

    @ManyToOne
    @JoinColumn(name = "MaBDX")
    private BaiDauXe baiDauXe;

    @ManyToOne
    @JoinColumn(name = "MaTuyen")
    private Tuyen tuyen;

    @ManyToOne
    @JoinColumn(name = "MaLX")
    private LoaiXe loaiXe;

    @ManyToOne
    @JoinColumn(name = "giaveId")
    private GiaVe giaVe;

    @OneToMany(mappedBy = "chuyenXe", cascade = CascadeType.ALL)
    private List<Ghe> ghes;

    @OneToMany(mappedBy = "chuyenXe", cascade = CascadeType.ALL)
    private List<HoaDon> hoaDons;

    public ChuyenXe(Long maChuyenXe, Long soViTriConTrong, Double giaTriChuyenXe, Date ngayChay, Xe xe, BaiDauXe baiDauXe, Tuyen tuyen, LoaiXe loaiXe, GiaVe giaVe, List<Ghe> ghes, List<HoaDon> hoaDons) {
        MaChuyenXe = maChuyenXe;
        SoViTriConTrong = soViTriConTrong;
        GiaTriChuyenXe = giaTriChuyenXe;
        NgayChay = ngayChay;
        this.xe = xe;
        this.baiDauXe = baiDauXe;
        this.tuyen = tuyen;
        this.loaiXe = loaiXe;
        this.giaVe = giaVe;
        this.ghes = ghes;
        this.hoaDons = hoaDons;
    }

    public ChuyenXe() {
    }

    public Long getMaChuyenXe() {
        return MaChuyenXe;
    }

    public void setMaChuyenXe(Long maChuyenXe) {
        MaChuyenXe = maChuyenXe;
    }

    public Long getSoViTriConTrong() {
        return SoViTriConTrong;
    }

    public void setSoViTriConTrong(Long soViTriConTrong) {
        SoViTriConTrong = soViTriConTrong;
    }

    public Double getGiaTriChuyenXe() {
        return GiaTriChuyenXe;
    }

    public void setGiaTriChuyenXe(Double giaTriChuyenXe) {
        GiaTriChuyenXe = giaTriChuyenXe;
    }

    public Date getNgayChay() {
        return NgayChay;
    }

    public void setNgayChay(Date ngayChay) {
        NgayChay = ngayChay;
    }

    public Xe getXe() {
        return xe;
    }

    public void setXe(Xe xe) {
        this.xe = xe;
    }

    public BaiDauXe getBaiDauXe() {
        return baiDauXe;
    }

    public void setBaiDauXe(BaiDauXe baiDauXe) {
        this.baiDauXe = baiDauXe;
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

    public GiaVe getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(GiaVe giaVe) {
        this.giaVe = giaVe;
    }

    public List<Ghe> getGhes() {
        return ghes;
    }

    public void setGhes(List<Ghe> ghes) {
        this.ghes = ghes;
    }

    public List<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(List<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
}
