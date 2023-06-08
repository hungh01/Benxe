package com.example.BenXe.Controller.NhanVien;

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
import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Model.KhachHang;
import com.example.BenXe.Model.LoaiTK;
import com.example.BenXe.Model.NhanVien;
import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Model.PhieuDatVe;
import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Model.Tuyen;
import com.example.BenXe.Model.Xe;
import com.example.BenXe.Service.BaiDauXeService;
import com.example.BenXe.Service.ChuXeService;
import com.example.BenXe.Service.GiaVeService;
import com.example.BenXe.Service.KhachHangService;
import com.example.BenXe.Service.LoaiTKService;
import com.example.BenXe.Service.NhanVienService;
import com.example.BenXe.Service.PheuDatVeService;
import com.example.BenXe.Service.PhieuDangKyTuyenService;
import com.example.BenXe.Service.TaiKhoanService;
import com.example.BenXe.Service.TuyenService;
import com.example.BenXe.Service.XeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/nhanvien")
public class HomeControllerAdmin {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private LoaiTKService loaiTKService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private ChuXeService chuXeService;
    @Autowired 
    private XeService xeService;
    @Autowired
    private PhieuDangKyTuyenService phieuDangKyTuyenService;
    @Autowired
    private PheuDatVeService phieuDatVeService;
    @Autowired
    private BaiDauXeService baiDauXeService;
    @Autowired
    private GiaVeService giaVeService;

    @Autowired
    private TuyenService tuyenService;
    @GetMapping
    public String index(Model model){
        List<PhieuDangKyTuyen> PDKTList = phieuDangKyTuyenService.getAllPhieuDangKyTuyens();
        Integer countPDKTWait = 0;
        List<ChuXe> chuXes = chuXeService.getAllChuXes();
        List<Xe> xes = xeService.getAllXes();
        List<BaiDauXe> baiDauXes = baiDauXeService.getAllBaiDauXes();
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        for (PhieuDangKyTuyen pdkt : PDKTList) 
            if(!pdkt.getTrangThai()){
            countPDKTWait++;
        }
        model.addAttribute("PDKTDoiDuyet",countPDKTWait);
        model.addAttribute("SoChuXe",chuXes.size());
        model.addAttribute("SoXe",xes.size());
        model.addAttribute("SoBDX",baiDauXes.size());
        model.addAttribute("SoKhachHang",khachHangs.size());
        
        return "nhanvien/home/index";
    }
    @GetMapping("/qlnhanvien")
    public String listNhanVien(Model model){
        List<NhanVien> nhanViens = nhanVienService.getAllNhanVien();
        model.addAttribute("NhanViens",nhanViens);
        return "nhanvien/home/listnhanvien";
    }
    @GetMapping("/qlkhachhang")
    public String listKhachHang(Model model){
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        model.addAttribute("KhachHangs", khachHangs);
        return "nhanvien/home/listkhachhang";
    }
    @GetMapping("/qlnhaxe")
    public String listNhaXe(Model model){
        List<ChuXe> nhaXes = chuXeService.getAllChuXes();
        model.addAttribute("nhaXes", nhaXes);
        return "nhanvien/home/listnhaxe";
    }
    @GetMapping("/qlxe")
    public String listXe(Model model){
        List<Xe> xes = xeService.getAllXes();
        model.addAttribute("xes",xes);
        return "nhanvien/home/listxe";
    }
    @GetMapping("/qldondangkytuyen")
    public String listDonDangKyTuyen(Model model){
        model.addAttribute("donDangKyTuyens",phieuDangKyTuyenService.getAllPhieuDangKyTuyens());
        return "nhanvien/home/listdon";
    }

    @GetMapping("/qlve")
    public String listVe(Model model){
        model.addAttribute("phieuDatVes", phieuDatVeService.getAllPhieuDatVes());
        return "nhanvien/home/listve";
    }

    @GetMapping("/qltuyen")
    public String listTuyen(Model model){
        model.addAttribute("tuyens",tuyenService.getAllTuyens());
        return "nhanvien/home/listtuyen";
    }

    @GetMapping("/qlgiave")
    public String listGiaVe(Model model){
        model.addAttribute("giaVes",giaVeService.getAllGiaVes());
        return "nhanvien/home/listgiave";
    }


    @GetMapping("/createnhanvien")
    public String registernhanvien(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("nhanVien",new NhanVien());
        return "nhanvien/taikhoan/createtknhanvien";
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
            return "nhanvien/taikhoan/createtknhanvien";
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
        return "redirect:/nhanvien";
    }
    @GetMapping("/createtuyen")
    public String themtuyen(Model model) {
        model.addAttribute("tuyen", new Tuyen());
        return "nhanvien/createttuyen";
    }

    @GetMapping("/duyetdangkytuyen/{id}")
    public String duyetdangkytuyenForm(@PathVariable("id") Long id,Model model){
        PhieuDangKyTuyen pdkt = null;
        for (PhieuDangKyTuyen pdkt1: phieuDangKyTuyenService.getAllPhieuDangKyTuyens())
            if(pdkt1.getPhieuDangKyTuyen().equals(id))
                pdkt = pdkt1;
        if(pdkt!= null){
            model.addAttribute("pdkt",pdkt);
            model.addAttribute("taiKhoanChuXe", new TaiKhoan());
            model.addAttribute("taiKhoanXe", new TaiKhoan());
            return "nhanvien/home/duyetdangkytuyen";
        }else
            return "not-found!";
    }

    @PostMapping("/duyetdangkytuyen/{id}")
    public String duyetdangkytuyen(@PathVariable("id") Long id, Model model, @ModelAttribute("pdkt") PhieuDangKyTuyen pdkt, @ModelAttribute("taiKhoanChuXe") TaiKhoan taiKhoanChuXe, @ModelAttribute("taiKhoanXe")TaiKhoan taiKhoanXe) {
            taiKhoanChuXe.setLoaitk(loaiTKService.getLoaiTkById(3L));
            taiKhoanXe.setLoaitk(loaiTKService.getLoaiTkById(4L));
            taiKhoanService.save(taiKhoanXe);
            taiKhoanService.save(taiKhoanChuXe);
            pdkt.setTrangThai(true);
            phieuDangKyTuyenService.save(pdkt);
            return "redirect:/nhanvien/qldondangkytuyen";
    }
}
