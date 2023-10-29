package com.example.BenXe.Controller.NhaXe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.BaiDauXe;
import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.CustomTaiKhoanDetail;

import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Model.Xe;
import com.example.BenXe.Service.BaiDauXeService;
import com.example.BenXe.Service.ChuyenXeService;
import com.example.BenXe.Service.LoaiXeService;
import com.example.BenXe.Service.PhieuDangKyTuyenService;
import com.example.BenXe.Service.TaiKhoanService;
import com.example.BenXe.Service.TuyenService;
import com.example.BenXe.Service.XeService;



@Controller
@RequestMapping("/nhaxe")
public class HomeControllerNhaXe {
    @Autowired
    private ChuyenXeService chuyenXeService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private BaiDauXeService baiDauXeService;
    @Autowired
    private TuyenService tuyenService;
    @Autowired
    private LoaiXeService loaiXeService;
    @Autowired
    private XeService xeService;
    @Autowired
    private PhieuDangKyTuyenService phieuDangKyTuyenService;
    @GetMapping
    public String index(Authentication authentication, Model model){
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<ChuXe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getChuXes();
        ChuXe chuXe = cx.get(0);
        List<Xe> xes= chuXe.getXes();
        model.addAttribute("soxe", xes.size());
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
    @GetMapping("/listchuyenxe")
    public String listchuyenxe(Model model, Authentication authentication){
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<ChuXe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getChuXes();
        ChuXe chuXe = cx.get(0);
        List<Xe> xes= chuXe.getXes();
        List<ChuyenXe> chuyenxes = new ArrayList<ChuyenXe>();
        for(Xe xe : xes)
            for(ChuyenXe chuyenXe : xe.getChuyenXes()){
                chuyenxes.add(chuyenXe);
            }
        model.addAttribute("chuyenxes", chuyenxes);
        return "nhaxe/home/listchuyenxe";
    }
    @GetMapping("/xemkhachhang/{id}")
    public String xemkhachhang(@PathVariable("id") Long id, Model model){
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("ves", chuyenXe.getPhieuDatVes());
        return "nhaxe/home/xemkhachhang";
    }
    @GetMapping("/xemhoadon/{id}")
    public String xemhoadon(@PathVariable("id") Long id, Model model){
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("hoaDons", chuyenXe.getHoaDons());
        return "nhaxe/home/listhoadon";
    }
    @GetMapping("/addxe")
    public String addxe(Model model){
        model.addAttribute("xe",new Xe());
        List<BaiDauXe> baiDauXes = new ArrayList<BaiDauXe>();
        for(BaiDauXe baiDauXe : baiDauXeService.getAllBaiDauXes())
            if(!baiDauXe.getTinhTrang())
            baiDauXes.add(baiDauXe);
        model.addAttribute("baiDauXes",baiDauXes);
        model.addAttribute("tuyens",tuyenService.getAllTuyens());
        model.addAttribute("loaiXes", loaiXeService.getAllLoaiXes());
        model.addAttribute("pdkt", new PhieuDangKyTuyen());
        return "nhaxe/home/addxe";
    }
    @PostMapping("/addxe")
    public String addxe(@ModelAttribute("xe") Xe xe, @ModelAttribute("pdkt") PhieuDangKyTuyen pdkt, 
            Authentication authentication){
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<ChuXe> cx = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getChuXes();
        ChuXe chuXe = cx.get(0);
        
        LocalDate date = LocalDate.now();
        pdkt.setThoiGianNopPhieu(date);

        List<PhieuDangKyTuyen> p = chuXe.getPhieuDangKyTuyens();
        p.add(pdkt);
        chuXe.setPhieuDangKyTuyens(p);
        
        List<Xe> x = chuXe.getXes();
        x.add(xe);
        chuXe.setXes(x);
        List<PhieuDangKyTuyen> pdkts = new ArrayList<PhieuDangKyTuyen>();
        pdkts.add(pdkt);

        pdkt.chuXe(chuXe);
        pdkt.xe(xe);
        pdkt.TrangThai(false);

        xe.setPhieuDangKyTuyens(pdkts);
        xe.chuXe(chuXe);


        //pdkt.giaVe(giaVeService.FindIdByMaLXMaTuyen(pdkt.getLoaiXe().getMaLX(), pdkt.getTuyen()));
        xeService.save(xe);
        phieuDangKyTuyenService.save(pdkt);
        
        return "redirect:/nhaxe/listxe";
    }
}
