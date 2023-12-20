package com.example.BenXe.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.BaiDauXe;
import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.DiaDiem;
import com.example.BenXe.Model.Ghe;
import com.example.BenXe.Model.GheCuaChuyen;
import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Model.Tuyen;
import com.example.BenXe.Model.Xe;
import com.example.BenXe.Service.BaiDauXeService;
import com.example.BenXe.Service.ChuXeService;
import com.example.BenXe.Service.ChuyenXeService;
import com.example.BenXe.Service.DiaDiemService;
import com.example.BenXe.Service.GheCuaChuyenService;
import com.example.BenXe.Service.GiaVeService;
import com.example.BenXe.Service.LoaiXeService;
import com.example.BenXe.Service.PhieuDangKyTuyenService;
import com.example.BenXe.Service.TuyenService;
import com.example.BenXe.Service.XeService;


@Controller

@RequestMapping()
public class HomeController {

    @Autowired
    private TuyenService tuyenService;
    @Autowired 
    private LoaiXeService loaiXeService;
    @Autowired
    private GiaVeService giaVeService;
    @Autowired
    private PhieuDangKyTuyenService pdktService;
    @Autowired
    private ChuXeService chuXeService;
    @Autowired
    private XeService xeService;
    @Autowired
    private BaiDauXeService baiDauXeService;
    @Autowired 
    private PhieuDangKyTuyenService phieuDangKyTuyenService;
    @Autowired
    private ChuyenXeService chuyenXeService;
    @Autowired
    private GheCuaChuyenService gheCuaChuyenService;

    @Autowired
    private DiaDiemService diaDiemService;
    
        @GetMapping
        public String home(Model model){
            List<DiaDiem> diadiems = diaDiemService.getDiaDiemOfTuyen();
            List<String> tuyens = new ArrayList<String>();
            for(DiaDiem diaDiem : diadiems){
                tuyens.add(diaDiem.getDiaDiem());
            }
            LocalDate ngaydi = null;
            model.addAttribute("tuyens", tuyens);
            model.addAttribute("ngaydi", ngaydi);
            return "index";
        }
        @GetMapping("/timve")
        public String timve(String diemdi,String diemden, LocalDate ngaydi, Model model) {
        List<ChuyenXe> findChuyenXes = new ArrayList<ChuyenXe>();
        List<ChuyenXe> cxs = chuyenXeService.getChuyenXeByNgayChay(ngaydi);
        for (ChuyenXe cx : cxs)
            if (cx.getTuyen().getDiemDi().getDiaDiem().equals(diemdi) && cx.getTuyen().getDiemDen().getDiaDiem().equals(diemden) && cx.getSoViTriConTrong() > 0)
                findChuyenXes.add(cx);
        List<GheCuaChuyen> gheCuaChuyens =gheCuaChuyenService.getAllGheCuaChuyens();
        List<GheCuaChuyen> listghechon = new ArrayList<GheCuaChuyen>();
        model.addAttribute("chuyenxes", findChuyenXes);
        model.addAttribute("gheCuaChuyens", gheCuaChuyens);
        model.addAttribute("listghechon", listghechon);
        return "timve";
        }
        @GetMapping("/contact")
        public String contact(Model model){
            model.addAttribute("tuyens", tuyenService.getAllTuyens());
            List<BaiDauXe> bdxs = new ArrayList<BaiDauXe>();
            for(BaiDauXe bdx : baiDauXeService.getAllBaiDauXes())
                if(!bdx.getTinhTrang())
                    bdxs.add(bdx);
            model.addAttribute("baiDauXes", bdxs);
            model.addAttribute("pdkt", new PhieuDangKyTuyen());
            model.addAttribute("chuXe", new ChuXe());
            model.addAttribute("xe", new Xe());
            return "contact";
        }

        @PostMapping("/contact")
        public String dangkytuyen(@ModelAttribute("chuXe") ChuXe chuXe,@ModelAttribute("pdkt") PhieuDangKyTuyen pdkt, @ModelAttribute("xe") Xe xe, @ModelAttribute("baiDauXe") BaiDauXe baiDauXe){
            LocalDate date = LocalDate.now();
            pdkt.setThoiGianNopPhieu(date);
            xe.setSoGhe(36L);
            xe.setHangHoa(10L);
            List<PhieuDangKyTuyen> p = new ArrayList<PhieuDangKyTuyen>();
            p.add(pdkt);
            chuXe.setPhieuDangKyTuyens(p);
            List<Xe> x = new ArrayList<Xe>();
            x.add(xe);
            chuXe.setXes(x);
            xe.setLoaiXe(loaiXeService.getLoaiXeById(1L));
            xe.setPhieuDangKyTuyens(p);
            xe.setChuXe(chuXe);
            pdkt.setLoaiXe(loaiXeService.getLoaiXeById(1L));
            pdkt.setChuXe(chuXe);
            pdkt.setXe(xe);
            pdkt.setTrangThai(false);
            //pdkt.giaVe(giaVeService.FindIdByMaLXMaTuyen(pdkt.getLoaiXe().getMaLX(), pdkt.getTuyen()));
            chuXeService.save(chuXe);
            xeService.save(xe);
            phieuDangKyTuyenService.save(pdkt);
            return "redirect:/";
        }
        @GetMapping("/support")
        public String getSupport(){
            return"support";
        }
}
