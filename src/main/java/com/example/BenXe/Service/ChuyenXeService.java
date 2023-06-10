package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Repository.IChuyenXeRepository;

@Service
public class ChuyenXeService {
    @Autowired
    private IChuyenXeRepository chuyenXeRepository;
    public List<ChuyenXe> getAllChuXes(){
        return chuyenXeRepository.findAll();
    }
    public ChuyenXe getChuXeById(Long id){
        Optional<ChuyenXe> optional = chuyenXeRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( ChuyenXe chuyenXe){
        chuyenXeRepository.save(chuyenXe);
    }
}
