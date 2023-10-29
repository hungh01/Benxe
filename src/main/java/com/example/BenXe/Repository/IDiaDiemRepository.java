package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.DiaDiem;

@Repository
public interface IDiaDiemRepository extends JpaRepository<DiaDiem,Long>{
    
}
