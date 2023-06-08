package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.NhanVien;

@Repository
public interface INhanVienRepository extends JpaRepository<NhanVien, Long>{

}
