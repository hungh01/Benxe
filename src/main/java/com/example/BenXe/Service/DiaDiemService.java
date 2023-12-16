package com.example.BenXe.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.DiaDiem;
import com.example.BenXe.Repository.IDiaDiemRepository;

@Service
public class DiaDiemService {
    @Autowired
    private IDiaDiemRepository diaDiemRepository;
    public List<DiaDiem> getAllDiaDiems(){
        return diaDiemRepository.findAll();
    }
    public DiaDiem getDiaDiemById(Long id){
        Optional<DiaDiem> optional = diaDiemRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( DiaDiem chuyenXe){
        diaDiemRepository.save(chuyenXe);
    }
}
