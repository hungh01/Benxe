package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaKH;

    @Column(name = "TenKH")
    private String TenKH;

    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "Email")
    private String Email;

    @Column(name = "SDT")
    private String SDT;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "Id")
    private TaiKhoan taiKhoan;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<PhieuDatVe> phieuDatVes;



    public KhachHang() {
    }

    public KhachHang(Long MaKH, String TenKH, String DiaChi, String Email, String SDT, TaiKhoan taiKhoan, List<PhieuDatVe> phieuDatVes) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SDT = SDT;
        this.taiKhoan = taiKhoan;
        this.phieuDatVes = phieuDatVes;
    }

    public Long getMaKH() {
        return this.MaKH;
    }

    public void setMaKH(Long MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return this.TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getDiaChi() {
        return this.DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return this.SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public TaiKhoan getTaiKhoan() {
        return this.taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public List<PhieuDatVe> getPhieuDatVes() {
        return this.phieuDatVes;
    }

    public void setPhieuDatVes(List<PhieuDatVe> phieuDatVes) {
        this.phieuDatVes = phieuDatVes;
    }

    public KhachHang MaKH(Long MaKH) {
        setMaKH(MaKH);
        return this;
    }

    public KhachHang TenKH(String TenKH) {
        setTenKH(TenKH);
        return this;
    }

    public KhachHang DiaChi(String DiaChi) {
        setDiaChi(DiaChi);
        return this;
    }

    public KhachHang Email(String Email) {
        setEmail(Email);
        return this;
    }

    public KhachHang SDT(String SDT) {
        setSDT(SDT);
        return this;
    }

    public KhachHang taiKhoan(TaiKhoan taiKhoan) {
        setTaiKhoan(taiKhoan);
        return this;
    }

    public KhachHang phieuDatVes(List<PhieuDatVe> phieuDatVes) {
        setPhieuDatVes(phieuDatVes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof KhachHang)) {
            return false;
        }
        KhachHang khachHang = (KhachHang) o;
        return Objects.equals(MaKH, khachHang.MaKH) && Objects.equals(TenKH, khachHang.TenKH) && Objects.equals(DiaChi, khachHang.DiaChi) && Objects.equals(Email, khachHang.Email) && Objects.equals(SDT, khachHang.SDT) && Objects.equals(taiKhoan, khachHang.taiKhoan) && Objects.equals(phieuDatVes, khachHang.phieuDatVes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaKH, TenKH, DiaChi, Email, SDT, taiKhoan, phieuDatVes);
    }

    @Override
    public String toString() {
        return "{" +
            " MaKH='" + getMaKH() + "'" +
            ", TenKH='" + getTenKH() + "'" +
            ", DiaChi='" + getDiaChi() + "'" +
            ", Email='" + getEmail() + "'" +
            ", SDT='" + getSDT() + "'" +
            ", taiKhoan='" + getTaiKhoan() + "'" +
            ", phieuDatVes='" + getPhieuDatVes() + "'" +
            "}";
    }

    
}
