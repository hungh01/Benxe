package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.Ghe;
import com.example.BenXe.Repository.IGheRepository;

@Service
public class GheService {
    @Autowired
    private IGheRepository gheRepository;
    public List<Ghe> getAllGhes(){
        return gheRepository.findAll();
    }
    public Ghe getGheById(Long id){
        Optional<Ghe> optional =gheRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( Ghe ghe){
        gheRepository.save(ghe);
    }
}
