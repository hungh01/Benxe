package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.ChuyenXe;

@Repository
public interface IChuyenXeRepository extends JpaRepository<ChuyenXe, Long> {
    
}
