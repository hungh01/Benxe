package com.example.BenXe.Controller;

import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private TaiKhoanService taiKhoanService;
    @GetMapping("/index")
    public String home(){
//        List<TaiKhoan> taiKhoans = taiKhoanService.getAllTaiKhoan();
//        model.addAttribute("taiKhoans", taiKhoans);
        return "index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

}
