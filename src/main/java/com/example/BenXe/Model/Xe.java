package com.example.BenXe.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Xe")
public class Xe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BKS;
    @Column(name = "TenTaiXe")
    private String TenTaiXe;
    @Column(name = "TenPhuXe")
    private String TenPhuXe;
    @Column(name = "SDT")
    private String SDT;
    @Column(name = "Email")
    private String Email;
    @Column(name = "SoGhe")
    private Long SoGhe;
    @Column(name = "NamSX")
    private Long NamSX;
    @ManyToOne
    @JoinColumn(name = "MaLX")
    private LoaiXe loaiXe;

    @ManyToOne
    @JoinColumn(name = "MaBDX")
    private BaiDauXe baiDauXe;

    @ManyToOne
    @JoinColumn(name = "Id")
    private TaiKhoan taiKhoan;

    @OneToMany(mappedBy = "xe", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    @OneToMany(mappedBy = "xe", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes;

    public Xe(Long BKS, String tenTaiXe, String tenPhuXe, String SDT, String email, Long soGhe, Long namSX, LoaiXe loaiXe, BaiDauXe baiDauXe, TaiKhoan taiKhoan, List<PhieuDangKyTuyen> phieuDangKyTuyens, List<ChuyenXe> chuyenXes) {
        this.BKS = BKS;
        TenTaiXe = tenTaiXe;
        TenPhuXe = tenPhuXe;
        this.SDT = SDT;
        Email = email;
        SoGhe = soGhe;
        NamSX = namSX;
        this.loaiXe = loaiXe;
        this.baiDauXe = baiDauXe;
        this.taiKhoan = taiKhoan;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
        this.chuyenXes = chuyenXes;
    }

    public Xe() {
    }

    public Long getBKS() {
        return BKS;
    }

    public void setBKS(Long BKS) {
        this.BKS = BKS;
    }

    public String getTenTaiXe() {
        return TenTaiXe;
    }

    public void setTenTaiXe(String tenTaiXe) {
        TenTaiXe = tenTaiXe;
    }

    public String getTenPhuXe() {
        return TenPhuXe;
    }

    public void setTenPhuXe(String tenPhuXe) {
        TenPhuXe = tenPhuXe;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Long getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(Long soGhe) {
        SoGhe = soGhe;
    }

    public Long getNamSX() {
        return NamSX;
    }

    public void setNamSX(Long namSX) {
        NamSX = namSX;
    }

    public LoaiXe getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public BaiDauXe getBaiDauXe() {
        return baiDauXe;
    }

    public void setBaiDauXe(BaiDauXe baiDauXe) {
        this.baiDauXe = baiDauXe;
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

    public List<ChuyenXe> getChuyenXes() {
        return chuyenXes;
    }

    public void setChuyenXes(List<ChuyenXe> chuyenXes) {
        this.chuyenXes = chuyenXes;
    }
}
