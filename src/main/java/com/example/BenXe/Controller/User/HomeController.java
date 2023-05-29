package com.example.BenXe.Controller.User;

// import com.example.BenXe.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

@RequestMapping("/")
public class HomeController {

    @Autowired
    // private TaiKhoanService taiKhoanService;
    @GetMapping
    public String home(){
//        List<TaiKhoan> taiKhoans = taiKhoanService.getAllTaiKhoan();
//        model.addAttribute("taiKhoans", taiKhoans);
        return "user/home/index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "user/home/contact";
    }

}
