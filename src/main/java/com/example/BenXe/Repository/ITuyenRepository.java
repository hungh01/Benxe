package com.example.BenXe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.Tuyen;


@Repository
public interface ITuyenRepository extends JpaRepository<Tuyen, Long>{
    @Query(value = "SELECT DISTINCT v.dia_diem FROM tuyen u INNER JOIN dia_diem v ON u.diem_den = v.ma_dia_diem", nativeQuery = true)
    List<String> getDiemDen();

}
