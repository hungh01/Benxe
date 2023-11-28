package com.example.BenXe.Controller.Xe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Model.Ghe;
import com.example.BenXe.Model.GheCuaChuyen;
import com.example.BenXe.Model.HoaDon;
import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Model.Xe;
import com.example.BenXe.Service.ChuyenXeService;
import com.example.BenXe.Service.GheCuaChuyenService;
import com.example.BenXe.Service.GheService;
import com.example.BenXe.Service.HoaDonService;
import com.example.BenXe.Service.TaiKhoanService;

@Controller
@RequestMapping("/xe")
public class HomeControllerXe {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private ChuyenXeService chuyenXeService;
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private GheService gheService;
    @Autowired
    private GheCuaChuyenService gheCuaChuyenService;

    @GetMapping
    public String chuyenxe(Model model, Authentication authentication) {
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<Xe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getXes();
        Xe xe = cx.get(0);
        model.addAttribute("chuyenxes", xe.getChuyenXes());
        return "xe/home/listchuyenxe";
    }

    @GetMapping("/themchuyen")
    public String themchuyenxe(Model model) {
        model.addAttribute("chuyen", new ChuyenXe());
        return "xe/home/themchuyen";
    }

    @PostMapping("/themchuyen")
    public String themchuyen(Model model, Authentication authentication, @ModelAttribute("chuyen") ChuyenXe chuyen) {
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<Xe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getXes();
        Xe xe = cx.get(0);
        PhieuDangKyTuyen pdkt = xe.getPhieuDangKyTuyens().get(0);

        chuyen.setSoViTriConTrong(xe.getSoGhe());
        chuyen.setXe(xe);
        chuyen.setTuyen(pdkt.getTuyen());
        chuyen.setLoaiXe(xe.getLoaiXe());
        chuyen.setGiaVe(pdkt.getGiaVe());

        chuyenXeService.save(chuyen);

        List<Ghe> ghes = gheService.getAllGhes();
        for(Ghe ghe : ghes) {
            GheCuaChuyen gheCuaChuyen = new GheCuaChuyen();
            gheCuaChuyen.setChuyenXe(chuyen);
            gheCuaChuyen.setGhe(ghe);
            gheCuaChuyen.setTrangThai(false);
            gheCuaChuyenService.save(gheCuaChuyen);
        }
        return "redirect:/xe";

    }

    @GetMapping("/xemkhachhang/{id}")
    public String xemkhachhang(@PathVariable("id") Long id, Model model) {
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("ves", chuyenXe.getPhieuDatVes());
        return "xe/home/xemkhachhang";
    }

    @GetMapping("/hoadon/{id}")
    public String hoadon(@PathVariable("id") Long id, Model model) {
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("hoaDons", chuyenXe.getHoaDons());
        model.addAttribute("chuyenXe", chuyenXe);
        return "xe/home/hoadon";
    }

    @GetMapping("/themhoadon/{id}")
    public String themhoadon(@PathVariable("id") Long id, Model model) {
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("hoaDon", new HoaDon());
        model.addAttribute("chuyenXe", chuyenXe);
        return "xe/home/themhoadon";
    }

    @PostMapping("/themhoadon/{id}")
    public String themhoadon(@PathVariable("id") Long id, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        List<HoaDon> lstHD = chuyenXe.getHoaDons();
        hoaDon.setChuyenXe(chuyenXe);
        hoaDonService.save(hoaDon);
        lstHD.add(hoaDon);
        chuyenXe.setHoaDons(lstHD);
        return "redirect:/xe/hoadon/{id}";
    }

    @GetMapping("/chuyendi")
    public String chuyendi() {
        return "xe/home/chuyendi";
    }

    // @GetMapping("/getData")
    // public ResponseEntity<?> getData(Authentication authentication) {
    //     CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
    //     List<Xe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getXes();
    //     Xe xe = cx.get(0);
    //     // Fetch data from your data source
    //     List<ChuyenXe> data = xe.getChuyenXes();
    //     return ResponseEntity.ok(data);
    // }

}
