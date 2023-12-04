package com.example.BenXe.Controller.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// import com.example.BenXe.Service.TaiKhoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Model.DiaDiem;
import com.example.BenXe.Model.GheCuaChuyen;
import com.example.BenXe.Model.KhachHang;
import com.example.BenXe.Model.PhieuDatVe;
import com.example.BenXe.Service.ChuyenXeService;
import com.example.BenXe.Service.GheCuaChuyenService;
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
    @Autowired
    private GheCuaChuyenService gheCuaChuyenService;
    @GetMapping
    public String home(Model model) {
        List<String> tuyens = tuyenService.getDiemDen();
        LocalDate ngaydi = null;
        model.addAttribute("tuyens", tuyens);
        model.addAttribute("ngaydi", ngaydi);
        return "index";
    }

    @GetMapping("/xemvedadat")
    public String getXemvedadat(Authentication authentication, Model model) {
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<KhachHang> kh = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getKhachHangs();
        KhachHang khachHang = kh.get(0);
        model.addAttribute("phieuDatVes", khachHang.getPhieuDatVes());
        return "user/home/xemvedadat";
    }
    

    @GetMapping("/datve/{id}")
    public String datveForm(@PathVariable("id") Long id, Authentication authentication, Model model) {
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<KhachHang> kh = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getKhachHangs();
        KhachHang khachHang = kh.get(0);
        ChuyenXe cx = chuyenXeService.getChuyenXeById(id);
        PhieuDatVe phieuDatVe = new PhieuDatVe();
        phieuDatVe.setHangHoa(0L);
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("chuyenXe", cx);
        model.addAttribute("phieuDatVe", phieuDatVe);
        model.addAttribute("soGheSpan", new String());
        return "user/home/datve";
    }

    @PostMapping("/datve/{id}")
    public String datve(@PathVariable("id") Long id, @ModelAttribute("phieuDatVe") PhieuDatVe phieuDatVe,@ModelAttribute("soGheSpan") String soGheSpan,
        Authentication authentication) {
        List<String> list = new ArrayList<String>();
        if(soGheSpan.length()==0){
            String s = "redirect:/khachhang/datve/"+id.toString();
            return s;
        }
        soGheSpan +=", ";
        while (soGheSpan.length() > 0){
            String temp="";
            if(soGheSpan.charAt(3) == ','){
                temp += soGheSpan.substring(0, 3);
                list.add(temp);
                soGheSpan = soGheSpan.substring(5);
            }else
            if(soGheSpan.charAt(2) == ','){
                temp += soGheSpan.substring(0, 2);
                list.add(temp);
                soGheSpan = soGheSpan.substring(4);
            }
        }
        System.out.println(list);
        // ----- get user
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<KhachHang> kh = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getKhachHangs();
        KhachHang khachHang = kh.get(0);
        // ----- get chuyenxe
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        // ----set list pdv cho khachhang
        List<PhieuDatVe> pdvs = khachHang.getPhieuDatVes();
        pdvs.add(phieuDatVe);
        
        khachHang.setPhieuDatVes(pdvs);
        phieuDatVe.setKhachHang(khachHang);
        // -----set inf cho chuyenXe
        phieuDatVe.setChuyenXe(chuyenXe);
        List<PhieuDatVe> pdvscx = chuyenXe.getPhieuDatVes();
        pdvscx.add(phieuDatVe);
        chuyenXe.setPhieuDatVes(pdvscx);
        chuyenXe.SoViTriConTrong(chuyenXe.getSoViTriConTrong() - 1);
        // -----set inf phiếu đặt vé
        phieuDatVe.setNgayDat(LocalDate.now());
        phieuDatVe.setTinhTrangVe("Đã đặt");
        //------set TrangThaiGhe
        for (String s : list){
            GheCuaChuyen ghe = gheCuaChuyenService.getGheCuaChuyenByChuyenGhe(s,id);
            ghe.setTrangThai(true);
            gheCuaChuyenService.save(ghe);
        }
        // ----- lưu
        phieuDatVeService.save(phieuDatVe);

        khachHangService.save(khachHang);

        chuyenXeService.save(chuyenXe);

        return "redirect:/khachhang";
    }
}
