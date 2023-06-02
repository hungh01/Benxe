package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "BaiDauXe")
public class BaiDauXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaBDX;

    @Column(name = "MoTaViTri")
    private String MoTaViTri;

    @Column(name = "TinhTrang")
    private Long TinhTrang;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "baiDauXe", cascade = CascadeType.ALL)
    private List<Xe> xes;

    public BaiDauXe(Long maBDX, String moTaViTri, Long tinhTrang, List<Xe> xes) {
        MaBDX = maBDX;
        MoTaViTri = moTaViTri;
        TinhTrang = tinhTrang;
        this.xes = xes;
    }

    public BaiDauXe() {
    }

    public Long getMaBDX() {
        return MaBDX;
    }

    public void setMaBDX(Long maBDX) {
        MaBDX = maBDX;
    }

    public String getMoTaViTri() {
        return MoTaViTri;
    }

    public void setMoTaViTri(String moTaViTri) {
        MoTaViTri = moTaViTri;
    }

    public Long getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Long tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public List<Xe> getXes() {
        return xes;
    }

    public void setXes(List<Xe> xes) {
        this.xes = xes;
    }
}
