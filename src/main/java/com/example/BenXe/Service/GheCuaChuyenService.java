package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.GheCuaChuyen;
import com.example.BenXe.Repository.IGheCuaChuyenRepository;


@Service
public class GheCuaChuyenService {
    @Autowired
    private IGheCuaChuyenRepository gheCuaChuyenRepository;
    public List<GheCuaChuyen> getAllGheCuaChuyens(){
        return gheCuaChuyenRepository.findAll();
    }
    public List<GheCuaChuyen> getAllGheCuaVe(Long id){
        return gheCuaChuyenRepository.findGheCuaChuyenbyPDV(id);
    }

    public GheCuaChuyen getGheCuaChuyenById(Long id){
        Optional<GheCuaChuyen> optional =gheCuaChuyenRepository.findById(id);
        return optional.orElse(null);
    }
    public GheCuaChuyen getGheCuaChuyenByChuyenGhe(String gheChuyenGhe,Long chuyen){
        GheCuaChuyen optional = gheCuaChuyenRepository.findGheCuaChuyenbyChuyenGhe(gheChuyenGhe,chuyen);
        return optional;
    }
    public void save(GheCuaChuyen gheCuaChuyen){
        gheCuaChuyenRepository.save(gheCuaChuyen);
    }
}
