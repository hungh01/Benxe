package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Ghe")
public class Ghe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ghe;

    @Column(name = "MoTa")
    private String MoTa;

    @Column(name = "Tang")
    private Boolean Tang;
    

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "ghe", cascade = CascadeType.ALL)
    private List<GheCuaChuyen> gheCuaChuyens;


    public Ghe() {
    }

    public Ghe(Long Ghe, String MoTa, Boolean Tang, List<GheCuaChuyen> gheCuaChuyens) {
        this.Ghe = Ghe;
        this.MoTa = MoTa;
        this.Tang = Tang;
        this.gheCuaChuyens = gheCuaChuyens;
    }

    public Long getGhe() {
        return this.Ghe;
    }

    public void setGhe(Long Ghe) {
        this.Ghe = Ghe;
    }

    public String getMoTa() {
        return this.MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public Boolean isTang() {
        return this.Tang;
    }

    public Boolean getTang() {
        return this.Tang;
    }

    public void setTang(Boolean Tang) {
        this.Tang = Tang;
    }

    public List<GheCuaChuyen> getGheCuaChuyens() {
        return this.gheCuaChuyens;
    }

    public void setGheCuaChuyens(List<GheCuaChuyen> gheCuaChuyens) {
        this.gheCuaChuyens = gheCuaChuyens;
    }

    public Ghe Ghe(Long Ghe) {
        setGhe(Ghe);
        return this;
    }

    public Ghe MoTa(String MoTa) {
        setMoTa(MoTa);
        return this;
    }

    public Ghe Tang(Boolean Tang) {
        setTang(Tang);
        return this;
    }

    public Ghe gheCuaChuyens(List<GheCuaChuyen> gheCuaChuyens) {
        setGheCuaChuyens(gheCuaChuyens);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ghe)) {
            return false;
        }
        Ghe ghe = (Ghe) o;
        return Objects.equals(Ghe, ghe.Ghe) && Objects.equals(MoTa, ghe.MoTa) && Objects.equals(Tang, ghe.Tang) && Objects.equals(gheCuaChuyens, ghe.gheCuaChuyens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Ghe, MoTa, Tang, gheCuaChuyens);
    }

    @Override
    public String toString() {
        return "{" +
            " Ghe='" + getGhe() + "'" +
            ", MoTa='" + getMoTa() + "'" +
            ", Tang='" + isTang() + "'" +
            ", gheCuaChuyens='" + getGheCuaChuyens() + "'" +
            "}";
    }

}
