package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.Tuyen;

@Repository
public interface ITuyenRepository extends JpaRepository<Tuyen, Long>{
    
}
