package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "GiaVe")
@Embeddable
public class GiaVe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giaveId;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaTuyen")
    private Tuyen tuyen;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaLX")
    private LoaiXe loaiXe;

    @Column(name = "GiaHanhKhach")
    private Double GiaHanhKhach;

    @Column(name = "GiaHangHoa")
    private Double GiaHangHoa;

    @Column(name = "DichVuDiKem")
    private String DichVuDiKem;

    @OneToMany(mappedBy = "giaVe", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes;

    @OneToMany(mappedBy = "giaVe", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    public GiaVe() {
    }

    public GiaVe(Long giaveId, Tuyen tuyen, LoaiXe loaiXe, Double GiaHanhKhach, Double GiaHangHoa, String DichVuDiKem, List<ChuyenXe> chuyenXes, List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.giaveId = giaveId;
        this.tuyen = tuyen;
        this.loaiXe = loaiXe;
        this.GiaHanhKhach = GiaHanhKhach;
        this.GiaHangHoa = GiaHangHoa;
        this.DichVuDiKem = DichVuDiKem;
        this.chuyenXes = chuyenXes;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public Long getGiaveId() {
        return this.giaveId;
    }

    public void setGiaveId(Long giaveId) {
        this.giaveId = giaveId;
    }

    public Tuyen getTuyen() {
        return this.tuyen;
    }

    public void setTuyen(Tuyen tuyen) {
        this.tuyen = tuyen;
    }

    public LoaiXe getLoaiXe() {
        return this.loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public Double getGiaHanhKhach() {
        return this.GiaHanhKhach;
    }

    public void setGiaHanhKhach(Double GiaHanhKhach) {
        this.GiaHanhKhach = GiaHanhKhach;
    }

    public Double getGiaHangHoa() {
        return this.GiaHangHoa;
    }

    public void setGiaHangHoa(Double GiaHangHoa) {
        this.GiaHangHoa = GiaHangHoa;
    }

    public String getDichVuDiKem() {
        return this.DichVuDiKem;
    }

    public void setDichVuDiKem(String DichVuDiKem) {
        this.DichVuDiKem = DichVuDiKem;
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

    public GiaVe giaveId(Long giaveId) {
        setGiaveId(giaveId);
        return this;
    }

    public GiaVe tuyen(Tuyen tuyen) {
        setTuyen(tuyen);
        return this;
    }

    public GiaVe loaiXe(LoaiXe loaiXe) {
        setLoaiXe(loaiXe);
        return this;
    }

    public GiaVe GiaHanhKhach(Double GiaHanhKhach) {
        setGiaHanhKhach(GiaHanhKhach);
        return this;
    }

    public GiaVe GiaHangHoa(Double GiaHangHoa) {
        setGiaHangHoa(GiaHangHoa);
        return this;
    }

    public GiaVe DichVuDiKem(String DichVuDiKem) {
        setDichVuDiKem(DichVuDiKem);
        return this;
    }

    public GiaVe chuyenXes(List<ChuyenXe> chuyenXes) {
        setChuyenXes(chuyenXes);
        return this;
    }

    public GiaVe phieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        setPhieuDangKyTuyens(phieuDangKyTuyens);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GiaVe)) {
            return false;
        }
        GiaVe giaVe = (GiaVe) o;
        return Objects.equals(giaveId, giaVe.giaveId) && Objects.equals(tuyen, giaVe.tuyen) && Objects.equals(loaiXe, giaVe.loaiXe) && Objects.equals(GiaHanhKhach, giaVe.GiaHanhKhach) && Objects.equals(GiaHangHoa, giaVe.GiaHangHoa) && Objects.equals(DichVuDiKem, giaVe.DichVuDiKem) && Objects.equals(chuyenXes, giaVe.chuyenXes) && Objects.equals(phieuDangKyTuyens, giaVe.phieuDangKyTuyens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giaveId, tuyen, loaiXe, GiaHanhKhach, GiaHangHoa, DichVuDiKem, chuyenXes, phieuDangKyTuyens);
    }

    @Override
    public String toString() {
        return "{" +
            " giaveId='" + getGiaveId() + "'" +
            ", tuyen='" + getTuyen() + "'" +
            ", loaiXe='" + getLoaiXe() + "'" +
            ", GiaHanhKhach='" + getGiaHanhKhach() + "'" +
            ", GiaHangHoa='" + getGiaHangHoa() + "'" +
            ", DichVuDiKem='" + getDichVuDiKem() + "'" +
            ", chuyenXes='" + getChuyenXes() + "'" +
            ", phieuDangKyTuyens='" + getPhieuDangKyTuyens() + "'" +
            "}";
    }

    
}