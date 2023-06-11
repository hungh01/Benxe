package com.example.BenXe.Service;

import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Repository.ITaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {
    @Autowired
    private ITaiKhoanRepository taiKhoanRepository;
    @Autowired
    public List<TaiKhoan> getAllTaiKhoan(){
        return taiKhoanRepository.findAll();
    }
    public TaiKhoan getTaiKhoanByUsername(String username){
        return taiKhoanRepository.findByUsername(username);
    }
    public void save( TaiKhoan taiKhoan){
        taiKhoanRepository.save(taiKhoan);

    }


}
