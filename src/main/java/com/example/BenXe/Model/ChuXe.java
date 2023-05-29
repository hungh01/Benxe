package com.example.BenXe.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ChuXe")
public class ChuXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaCX;

    @Column(name = "TenChuXe")
    private String TenChuXe;

    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "Email")
    private String Email;

    @Column(name = "SDT")
    private String SDT;

    @ManyToOne
    @JoinColumn(name = "TenDangNhap")
    private TaiKhoan taiKhoan;

    @OneToMany(mappedBy = "chuXe", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    public ChuXe(Long maCX, String tenChuXe, String diaChi, String email, String SDT, TaiKhoan taiKhoan, List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        MaCX = maCX;
        TenChuXe = tenChuXe;
        DiaChi = diaChi;
        Email = email;
        this.SDT = SDT;
        this.taiKhoan = taiKhoan;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public ChuXe() {
    }

    public Long getMaCX() {
        return MaCX;
    }

    public void setMaCX(Long maCX) {
        MaCX = maCX;
    }

    public String getTenChuXe() {
        return TenChuXe;
    }

    public void setTenChuXe(String tenChuXe) {
        TenChuXe = tenChuXe;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public List<PhieuDangKyTuyen> getPhieuDangKyTuyens() {
        return phieuDangKyTuyens;
    }

    public void setPhieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }
}
