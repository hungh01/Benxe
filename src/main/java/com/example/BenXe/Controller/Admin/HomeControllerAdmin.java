package com.example.BenXe.Controller.Admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.BaiDauXe;
import com.example.BenXe.Model.GiaVe;
import com.example.BenXe.Model.LoaiTK;
import com.example.BenXe.Model.LoaiXe;
import com.example.BenXe.Model.NhanVien;
import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Model.Tuyen;
import com.example.BenXe.Service.BaiDauXeService;
import com.example.BenXe.Service.GiaVeService;
import com.example.BenXe.Service.LoaiTKService;
import com.example.BenXe.Service.LoaiXeService;
import com.example.BenXe.Service.NhanVienService;
import com.example.BenXe.Service.PheuDatVeService;
import com.example.BenXe.Service.TaiKhoanService;
import com.example.BenXe.Service.TuyenService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("admin")
public class HomeControllerAdmin {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private LoaiTKService loaiTKService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private PheuDatVeService phieuDatVeService;
    @Autowired
    private BaiDauXeService baiDauXeService;
    @Autowired
    private GiaVeService giaVeService;
    @Autowired
    private TuyenService tuyenService;
    @Autowired
    private LoaiXeService loaiXeService;

    @GetMapping()
    public String listNhanVien(Model model){
        List<NhanVien> nhanViens = nhanVienService.getAllNhanVien();
        model.addAttribute("NhanViens",nhanViens);
        return "admin/listnhanvien";
    }
    @GetMapping("/createnhanvien")
    public String registernhanvien(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("nhanVien",new NhanVien());
        return "admin/createtknhanvien";
    }
    @PostMapping("/createnhanvien")
    public String registernhanvien(@Valid @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,@ModelAttribute("nhanVien") NhanVien nhanVien,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "admin/createtknhanvien";
        }
        LoaiTK loaiTK = loaiTKService.getLoaiTkById(2L);
        taiKhoan.setLoaitk(loaiTK);
        taiKhoan.setMatKhau(new BCryptPasswordEncoder().encode(taiKhoan.getMatKhau()));

        List<NhanVien> khs = new ArrayList<NhanVien>();
        khs.add(nhanVien);
        taiKhoan.setNhanViens(khs);
        taiKhoanService.save(taiKhoan);
        nhanVien.setTaiKhoan(taiKhoan);
        nhanVienService.save(nhanVien);
        return "redirect:/admin";
    }

    @GetMapping("/qltuyen")
    public String listTuyen(Model model){
        model.addAttribute("tuyens",tuyenService.getAllTuyens());
        return "admin/listtuyen";
    }
    
    @GetMapping("/createtuyen")
    public String createtuyen(Model model){
        model.addAttribute("tuyen",new Tuyen());
        return "admin/createtuyen";
    }
    @PostMapping("createtuyen")
    public String createtuyen(@ModelAttribute("tuyen") Tuyen tuyen){
        tuyenService.save(tuyen);
        for(LoaiXe loaiXe : loaiXeService.getAllLoaiXes()){
            GiaVe gv = new GiaVe();
            gv.setLoaiXe(loaiXe);
            gv.setTuyen(tuyen);
            gv.setGia(0D);
        }
        return"redirect:/admin/qltuyen";
    }

    @GetMapping("/qlgiave")
    public String listGiaVe(Model model){
        model.addAttribute("giaVes",giaVeService.getAllGiaVes());
        return "admin/listgiave";
    }

    @GetMapping("/editgiave/{id}")
    public String editgiave(@PathVariable("id") Long id,Model model){
        GiaVe giaVe = giaVeService.getGiaVeById(id);
        model.addAttribute("giaVe", giaVe);
        return"admin/editgiave";
    }
    @PostMapping("/editgiave/{id}")
    public String editgiave(@PathVariable("id")Long id ,@ModelAttribute("giaVe") GiaVe giaVe) {
        GiaVe giaVe1 = giaVeService.getGiaVeById(id);
        giaVe1.setGia(giaVe.getGia());
        giaVeService.save(giaVe1);
        return"redirect:/admin/qlgiave";
    }
    @GetMapping("/qlbaidauxe")
    public String listBaiDauXe(Model model){
        model.addAttribute("baiDauXes",baiDauXeService.getAllBaiDauXes());
        return "admin/listbaidauxe";
    }
    @GetMapping("/createbaidauxe")
    public String createBDX(Model model){
        model.addAttribute("bdx",new BaiDauXe());
        return "admin/createbaidauxe";
    }
    @PostMapping("createbaidauxe")
    public String createtBDX(@ModelAttribute("bdx") BaiDauXe bdx, Model model){
        List<BaiDauXe> listBDX = new ArrayList<BaiDauXe>();
        listBDX = baiDauXeService.getAllBaiDauXes();
        for (BaiDauXe bdxchecked : listBDX) {
            if((bdxchecked.getMoTaViTri().equals(bdx.getMoTaViTri()) && bdxchecked.getThoiGianDi().equals(bdx.getThoiGianDi()) && bdxchecked.getThoiGianDen().equals(bdx.getThoiGianDen()))
                || bdxchecked.getMoTaViTri()==null || bdxchecked.getThoiGianDi()==null ||bdxchecked.getThoiGianDen() ==null){
                model.addAttribute("bdx",new BaiDauXe());
                return "admin/createbaidauxe";
            }
        }
        bdx.setTinhTrang(false);
        baiDauXeService.save(bdx);
        return"redirect:/admin/qlbaidauxe";
    }

}
