package com.example.BenXe.Controller.User;

import java.util.List;

// import com.example.BenXe.Service.TaiKhoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.Tuyen;
import com.example.BenXe.Service.TuyenService;

@Controller

@RequestMapping("/khachhang")
public class HomeControllerUser {

    @Autowired
    private TuyenService tuyenService;

    @GetMapping
    public String home(Model model){
        List<Tuyen> tuyens = tuyenService.getAllTuyens();
        model.addAttribute("tuyens",tuyens);
        return "user/home/index";
    }
    @GetMapping("/contact")
    public String contact(){
        return "user/home/contact";
    }

    @GetMapping("timve")
    public String timve(){
        return "user/home/timve";
    }
}
