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

    @GetMapping("/listxe")
    public String listxe(){
        return "nhaxe/home/listxe";
    }
    @GetMapping("listchuyenxe")
    public String listchuyenxe(){
        return "nhaxe/home/listchuyenxe";
    }
    @GetMapping("/listhoadon")
    public String listhoadon(){
        return "nhaxe/home/listhoadon";
    }
}
