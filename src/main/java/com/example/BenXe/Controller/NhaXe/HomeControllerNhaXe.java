package com.example.BenXe.Controller.NhaXe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/nhaxe")
public class HomeControllerNhaXe {


    @GetMapping
    public String index(){
        return "nhaxe/home/index";
    }
    @GetMapping("/home/contact")
    public String contact(){
        return "nhaxe/home/contact";
    }


}
