package com.example.BenXe.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "PhongBan")
public class PhongBan {
    @Id
    private String MaPhongBan;

    @Column(name = "TenPhongBan")
    private String TenPhongBan;

    @Column(name = "BoPhanPhuTrach")
    private String BoPhanPhuTrach;

    @Column(name = "MoTa")
    private String MoTa;

    public PhongBan(String maPhongBan, String tenPhongBan, String boPhanPhuTrach, String moTa) {
        MaPhongBan = maPhongBan;
        TenPhongBan = tenPhongBan;
        BoPhanPhuTrach = boPhanPhuTrach;
        MoTa = moTa;
    }

    public PhongBan() {
    }

    public String getMaPhongBan() {
        return MaPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        MaPhongBan = maPhongBan;
    }

    public String getTenPhongBan() {
        return TenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        TenPhongBan = tenPhongBan;
    }

    public String getBoPhanPhuTrach() {
        return BoPhanPhuTrach;
    }

    public void setBoPhanPhuTrach(String boPhanPhuTrach) {
        BoPhanPhuTrach = boPhanPhuTrach;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }
}
