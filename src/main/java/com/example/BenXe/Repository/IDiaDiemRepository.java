package com.example.BenXe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.DiaDiem;

@Repository
public interface IDiaDiemRepository extends JpaRepository<DiaDiem,Long>{
    @Query(value = "SELECT * FROM dia_diem WHERE dia_diem !='' ", nativeQuery = true)
    List<DiaDiem> finddiaDiemOfTuyen();
}
