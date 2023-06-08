package com.example.BenXe.Controller.User;

// import com.example.BenXe.Service.TaiKhoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Service.TuyenService;


@Controller

@RequestMapping("/khachhang")
public class HomeControllerUser {

    @Autowired
    private TuyenService tuyenService;

    @GetMapping
    public String home(Model model){
        model.addAttribute("tuyens", tuyenService.getAllTuyens());
        return "user/home/index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "user/home/contact";
    }

}
