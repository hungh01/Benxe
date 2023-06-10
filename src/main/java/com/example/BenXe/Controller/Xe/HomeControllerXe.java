package com.example.BenXe.Controller.Xe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Model.Xe;
import com.example.BenXe.Service.ChuyenXeService;
import com.example.BenXe.Service.TaiKhoanService;



@Controller
@RequestMapping("/xe")
public class HomeControllerXe {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private ChuyenXeService chuyenXeService;

    @GetMapping
    public String index(){
        return "xe/home/index";
    }
    
    @GetMapping("/listchuyenxe")
    public String chuyenxe(Model model, Authentication authentication){
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<Xe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getXes();
        Xe xe = cx.get(0);
        model.addAttribute("chuyenxes", xe.getChuyenXes());
        return "xe/home/listchuyenxe";
    }
    @GetMapping("/themchuyen")
    public String themchuyenxe(Model model){
        model.addAttribute("chuyen", new ChuyenXe());
        return "xe/home/themchuyen";
    }
    @PostMapping("/themchuyen")
    public String themchuyen(Model model, Authentication authentication,@ModelAttribute("chuyen") ChuyenXe chuyen){
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<Xe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getXes();
        Xe xe = cx.get(0);
        PhieuDangKyTuyen pdkt = xe.getPhieuDangKyTuyens().get(0);
        chuyen.setXe(xe);
        chuyen.setTuyen(pdkt.getTuyen());
        chuyen.setLoaiXe(xe.getLoaiXe());
        chuyen.setGiaVe(pdkt.getGiaVe());
        chuyenXeService.save(chuyen);
        return"redirect:/xe/chuyenxe";
    }
}
