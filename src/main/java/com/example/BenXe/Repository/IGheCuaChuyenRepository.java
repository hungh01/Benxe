package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.GheCuaChuyen;



@Repository
public interface IGheCuaChuyenRepository extends JpaRepository<GheCuaChuyen,Long>{
    
}
