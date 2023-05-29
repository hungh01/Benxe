package com.example.BenXe.Controller;

import com.example.BenXe.Model.LoaiTK;
import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Service.TaiKhoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @GetMapping("/login")
    public String login() {
        return "Login/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        return "Login/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "Login/register";
        }
        LoaiTK loaiTK;

        taiKhoan.setLoaitk(loaiTK);
        taiKhoan.setMatKhau(new BCryptPasswordEncoder().encode(taiKhoan.getMatKhau()));
        taiKhoanService.save(taiKhoan);
        return "redirect:/";
    }
}