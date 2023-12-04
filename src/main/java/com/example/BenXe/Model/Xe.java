package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Xe")
public class Xe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaXe;

    @Column(name="BKS")
    private String BKS;
    
    @Column(name = "TenTaiXe")
    private String TenTaiXe;
    @Column(name = "TenPhuXe")
    private String TenPhuXe;
    @Column(name = "SDT")
    private String SDT;
    @Column(name = "Email")
    private String Email;
    @Column(name = "NamSX")
    private Long NamSX;
    @Column(name = "HangHoa")
    private Long HangHoa;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaLX")
    private LoaiXe loaiXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaBDX")
    private BaiDauXe baiDauXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "Id")
    private TaiKhoan taiKhoan;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaCX")
    private ChuXe chuXe;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "xe", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "xe", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes;


    public Xe() {
    }

    public Xe(Long MaXe, String BKS, String TenTaiXe, String TenPhuXe, String SDT, String Email, Long NamSX, LoaiXe loaiXe, BaiDauXe baiDauXe, TaiKhoan taiKhoan, ChuXe chuXe, List<PhieuDangKyTuyen> phieuDangKyTuyens, List<ChuyenXe> chuyenXes) {
        this.MaXe = MaXe;
        this.BKS = BKS;
        this.TenTaiXe = TenTaiXe;
        this.TenPhuXe = TenPhuXe;
        this.SDT = SDT;
        this.Email = Email;
        this.NamSX = NamSX;
        this.loaiXe = loaiXe;
        this.baiDauXe = baiDauXe;
        this.taiKhoan = taiKhoan;
        this.chuXe = chuXe;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
        this.chuyenXes = chuyenXes;
    }

    public Long getMaXe() {
        return this.MaXe;
    }

    public void setMaXe(Long MaXe) {
        this.MaXe = MaXe;
    }

    public String getBKS() {
        return this.BKS;
    }

    public void setBKS(String BKS) {
        this.BKS = BKS;
    }

    public String getTenTaiXe() {
        return this.TenTaiXe;
    }

    public void setTenTaiXe(String TenTaiXe) {
        this.TenTaiXe = TenTaiXe;
    }

    public String getTenPhuXe() {
        return this.TenPhuXe;
    }

    public void setTenPhuXe(String TenPhuXe) {
        this.TenPhuXe = TenPhuXe;
    }

    public String getSDT() {
        return this.SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Long getNamSX() {
        return this.NamSX;
    }

    public void setNamSX(Long NamSX) {
        this.NamSX = NamSX;
    }

    public LoaiXe getLoaiXe() {
        return this.loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public BaiDauXe getBaiDauXe() {
        return this.baiDauXe;
    }

    public void setBaiDauXe(BaiDauXe baiDauXe) {
        this.baiDauXe = baiDauXe;
    }

    public TaiKhoan getTaiKhoan() {
        return this.taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public ChuXe getChuXe() {
        return this.chuXe;
    }

    public void setChuXe(ChuXe chuXe) {
        this.chuXe = chuXe;
    }

    public List<PhieuDangKyTuyen> getPhieuDangKyTuyens() {
        return this.phieuDangKyTuyens;
    }

    public void setPhieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public List<ChuyenXe> getChuyenXes() {
        return this.chuyenXes;
    }

    public void setChuyenXes(List<ChuyenXe> chuyenXes) {
        this.chuyenXes = chuyenXes;
    }

    public Xe MaXe(Long MaXe) {
        setMaXe(MaXe);
        return this;
    }

    public Xe BKS(String BKS) {
        setBKS(BKS);
        return this;
    }

    public Xe TenTaiXe(String TenTaiXe) {
        setTenTaiXe(TenTaiXe);
        return this;
    }

    public Xe TenPhuXe(String TenPhuXe) {
        setTenPhuXe(TenPhuXe);
        return this;
    }

    public Xe SDT(String SDT) {
        setSDT(SDT);
        return this;
    }

    public Xe Email(String Email) {
        setEmail(Email);
        return this;
    }

    public Xe NamSX(Long NamSX) {
        setNamSX(NamSX);
        return this;
    }

    public Xe loaiXe(LoaiXe loaiXe) {
        setLoaiXe(loaiXe);
        return this;
    }

    public Xe baiDauXe(BaiDauXe baiDauXe) {
        setBaiDauXe(baiDauXe);
        return this;
    }

    public Xe taiKhoan(TaiKhoan taiKhoan) {
        setTaiKhoan(taiKhoan);
        return this;
    }

    public Xe chuXe(ChuXe chuXe) {
        setChuXe(chuXe);
        return this;
    }

    public Xe phieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        setPhieuDangKyTuyens(phieuDangKyTuyens);
        return this;
    }

    public Xe chuyenXes(List<ChuyenXe> chuyenXes) {
        setChuyenXes(chuyenXes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Xe)) {
            return false;
        }
        Xe xe = (Xe) o;
        return Objects.equals(MaXe, xe.MaXe) && Objects.equals(BKS, xe.BKS) && Objects.equals(TenTaiXe, xe.TenTaiXe) && Objects.equals(TenPhuXe, xe.TenPhuXe) && Objects.equals(SDT, xe.SDT) && Objects.equals(Email, xe.Email) && Objects.equals(NamSX, xe.NamSX) && Objects.equals(loaiXe, xe.loaiXe) && Objects.equals(baiDauXe, xe.baiDauXe) && Objects.equals(taiKhoan, xe.taiKhoan) && Objects.equals(chuXe, xe.chuXe) && Objects.equals(phieuDangKyTuyens, xe.phieuDangKyTuyens) && Objects.equals(chuyenXes, xe.chuyenXes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaXe, BKS, TenTaiXe, TenPhuXe, SDT, Email, NamSX, loaiXe, baiDauXe, taiKhoan, chuXe, phieuDangKyTuyens, chuyenXes);
    }

    @Override
    public String toString() {
        return "{" +
            " MaXe='" + getMaXe() + "'" +
            ", BKS='" + getBKS() + "'" +
            ", TenTaiXe='" + getTenTaiXe() + "'" +
            ", TenPhuXe='" + getTenPhuXe() + "'" +
            ", SDT='" + getSDT() + "'" +
            ", Email='" + getEmail() + "'" +
            ", NamSX='" + getNamSX() + "'" +
            ", loaiXe='" + getLoaiXe() + "'" +
            ", baiDauXe='" + getBaiDauXe() + "'" +
            ", taiKhoan='" + getTaiKhoan() + "'" +
            ", chuXe='" + getChuXe() + "'" +
            ", phieuDangKyTuyens='" + getPhieuDangKyTuyens() + "'" +
            ", chuyenXes='" + getChuyenXes() + "'" +
            "}";
    }

}