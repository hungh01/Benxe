package com.example.BenXe.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class HomeControllerAdmin {

    @Autowired
    @GetMapping
    public String index(){
        return "admin/home/index";
    }
    @GetMapping("/home/contact")
    public String contact(){
        return "admin/home/contact";
    }


}
