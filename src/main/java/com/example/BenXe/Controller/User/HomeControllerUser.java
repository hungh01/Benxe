package com.example.BenXe.Controller.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// import com.example.BenXe.Service.TaiKhoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Model.KhachHang;
import com.example.BenXe.Model.PhieuDatVe;
import com.example.BenXe.Service.ChuyenXeService;
import com.example.BenXe.Service.KhachHangService;
import com.example.BenXe.Service.PhieuDatVeService;
import com.example.BenXe.Service.TaiKhoanService;
import com.example.BenXe.Service.TuyenService;

@Controller

@RequestMapping("/khachhang")
public class HomeControllerUser {

    @Autowired
    private TuyenService tuyenService;
    @Autowired
    private ChuyenXeService chuyenXeService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private PhieuDatVeService phieuDatVeService;

    @GetMapping
    public String home(Model model){
        List<String> tuyens = tuyenService.getDiemDen();
        LocalDate ngaydi = null;
        model.addAttribute("tuyens",tuyens);
        model.addAttribute("ngaydi",ngaydi);
        return "user/home/index";
    }
    @GetMapping("/timve")
    public String timve(String diemden, LocalDate ngaydi, Model model){
        List<ChuyenXe> findChuyenXes = new ArrayList<ChuyenXe>();
        List<ChuyenXe> cxs = chuyenXeService.getChuyenXeByNgayChay(ngaydi);
        for(ChuyenXe cx : cxs)
            if(cx.getTuyen().getDiemDen().equals(diemden) && cx.getSoViTriConTrong()>0)
                findChuyenXes.add(cx);
        model.addAttribute("chuyenxes", findChuyenXes);
        return"user/home/timve";
    }
    @GetMapping("/datve/{id}")
    public String datveForm(@PathVariable("id") Long id, Authentication authentication, Model model){
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<KhachHang> kh = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getKhachHangs();
        KhachHang khachHang = kh.get(0);
        ChuyenXe cx = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("chuyenXe", cx);
        model.addAttribute("phieuDatVe", new PhieuDatVe());
        return"user/home/datve";
    }
    @PostMapping("/datve/{id}")
    public String datve(@PathVariable("id") Long id,@ModelAttribute("phieuDatVe") PhieuDatVe phieuDatVe,Authentication authentication) {
        //----- get user
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<KhachHang> kh = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getKhachHangs();
        KhachHang khachHang = kh.get(0);
        //----- get chuyenxe
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        //----set list pdv cho khachhang
        List<PhieuDatVe> pdvs = khachHang.getPhieuDatVes();
        pdvs.add(phieuDatVe);
        khachHang.setPhieuDatVes(pdvs);
        phieuDatVe.setKhachHang(khachHang);
        //-----set inf cho chuyenXe
        phieuDatVe.setChuyenXe(chuyenXe);
        List<PhieuDatVe> pdvscx = chuyenXe.getPhieuDatVes();
        pdvscx.add(phieuDatVe);
        chuyenXe.setPhieuDatVes(pdvscx);
        chuyenXe.SoViTriConTrong(chuyenXe.getSoViTriConTrong()-1);
        //-----set inf phiếu đặt vé
        phieuDatVe.setNgayDat(LocalDate.now());
        if(phieuDatVe.getViTriLenXe()=="") phieuDatVe.setViTriLenXe("Bến xe miền đông");
        phieuDatVe.setTinhTrangVe("Đã đặt");
        //----- lưu 
        phieuDatVeService.save(phieuDatVe);
        
        khachHangService.save(khachHang);

        chuyenXeService.save(chuyenXe);

        
        return"redirect:/khachhang";

    }

    @GetMapping("timve")
    public String timve(){
        return "user/home/timve";
    }
}
