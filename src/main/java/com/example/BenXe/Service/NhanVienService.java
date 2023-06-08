package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.NhanVien;
import com.example.BenXe.Repository.INhanVienRepository;

@Service
public class NhanVienService {
    @Autowired
    private INhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllNhanVien(){
        return nhanVienRepository.findAll();
    }
    public NhanVien getNhanVienById(Long id){
        Optional<NhanVien> optional =nhanVienRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( NhanVien nhanVien){
        nhanVienRepository.save(nhanVien);
    }
    
}
