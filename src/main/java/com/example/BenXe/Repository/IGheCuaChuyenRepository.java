package com.example.BenXe.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.GheCuaChuyen;



@Repository
public interface IGheCuaChuyenRepository extends JpaRepository<GheCuaChuyen,Long>{
    @Query(value = "SELECT * FROM ghe_cua_chuyen a INNER JOIN ghe b ON a.ma_ghe=b.ghe INNER JOIN chuyen_xe c ON a.ma_chuyen=c.ma_chuyen_xe WHERE b.mo_ta=?1 AND c.ma_chuyen_xe=?2", nativeQuery = true)
    GheCuaChuyen findGheCuaChuyenbyChuyenGhe(String ghe, Long chuyen);
    @Query(value = "SELECT * FROM ghe_cua_chuyen a WHERE a.phieu_dat_ve=?1", nativeQuery = true)
    List<GheCuaChuyen> findGheCuaChuyenbyPDV(Long pdv);
}
