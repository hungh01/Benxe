package com.example.BenXe.Controller.NhaXe;

import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Model.Xe;
import com.example.BenXe.Service.TaiKhoanService;
import com.example.BenXe.Service.XeService;



@Controller
@RequestMapping("/nhaxe")
public class HomeControllerNhaXe {
    @Autowired
    private XeService xeService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @GetMapping
    public String index(){
        return "nhaxe/home/index";
    }

    @GetMapping("/listxe")
    public String listxe(Model model, Authentication authentication){
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<ChuXe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getChuXes();
        ChuXe chuXe = cx.get(0);
        model.addAttribute("xes", chuXe.getXes());
        return "nhaxe/home/listxe";
    }
}
