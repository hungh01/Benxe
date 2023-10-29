package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.HoaDon;

@Repository
public interface IHoaDonRepository extends JpaRepository<HoaDon,Long>{
    
}
