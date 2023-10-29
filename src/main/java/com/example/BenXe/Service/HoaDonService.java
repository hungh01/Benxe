package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.HoaDon;
import com.example.BenXe.Repository.IHoaDonRepository;

@Service
public class HoaDonService {
    @Autowired
    private IHoaDonRepository hoaDonRepository;
    public List<HoaDon> getAllHoaDons(){
        return hoaDonRepository.findAll();
    }
    public HoaDon getHoaDonById(Long id){
        Optional<HoaDon> optional =hoaDonRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( HoaDon hoaDon){
        hoaDonRepository.save(hoaDon);
    }
}
