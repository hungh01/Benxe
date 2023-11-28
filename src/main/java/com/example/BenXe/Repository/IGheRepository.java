package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.Ghe;

@Repository
public interface IGheRepository extends JpaRepository<Ghe,Long>{
    
}

