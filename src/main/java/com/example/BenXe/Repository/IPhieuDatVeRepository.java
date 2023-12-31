package com.example.BenXe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.PhieuDatVe;

import jakarta.transaction.Transactional;


@Repository
public interface IPhieuDatVeRepository extends JpaRepository<PhieuDatVe, Long>{
    @Query(value = "SELECT * FROM phieu_dat_ve a WHERE a.ma_chuyen_xe = ?1", nativeQuery = true)
    List<PhieuDatVe> findbychuyenXeId(Long chuyenXe);
    @Query(value = "SELECT * FROM phieu_dat_ve a INNER JOIN dia_diem b ON a.vi_tri_trung_chuyen=b.ma_dia_diem WHERE a.ma_chuyen_xe=?1", nativeQuery = true)
    List<PhieuDatVe> findpdvbychuyenXeId(Long chuyenXe);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM phieu_dat_ve WHERE ma_phieu_dat_ve = ?1", nativeQuery = true)
    void deleteById(Long Id);
}
