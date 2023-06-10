package com.example.BenXe.Controller.Xe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/xe")
public class HomeControllerXe {

    @GetMapping
    public String index(){
        return "xe/home/index";
    }
    
    @GetMapping("/home/contact")
    public String contact(){
        return "xe/home/contact";
    }


}
