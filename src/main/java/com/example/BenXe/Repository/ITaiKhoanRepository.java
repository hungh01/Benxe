package com.example.BenXe.Repository;

import com.example.BenXe.Model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    @Query("SELECT u FROM TaiKhoan u WHERE u.TenDangNhap = ?1")
    TaiKhoan findByUsername(String TenDangNhap);
}
