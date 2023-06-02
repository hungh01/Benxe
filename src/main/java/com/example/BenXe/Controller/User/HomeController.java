package com.example.BenXe.Controller.User;

// import com.example.BenXe.Service.TaiKhoanService;
import com.example.BenXe.Model.KhachHang;
import com.example.BenXe.Model.TaiKhoan;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

@RequestMapping("/")
public class HomeController {


    @GetMapping
    public String home(){
        return "user/home/index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "user/home/contact";
    }

}
