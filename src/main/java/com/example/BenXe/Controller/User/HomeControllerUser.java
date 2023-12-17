package com.example.BenXe.Controller.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

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
import com.example.BenXe.Service.DiaDiemService;
import com.example.BenXe.Service.GheCuaChuyenService;
import com.example.BenXe.Service.KhachHangService;
import com.example.BenXe.Service.PhieuDatVeService;
import com.example.BenXe.Service.TaiKhoanService;
import com.example.BenXe.Service.TuyenService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    @Autowired
    private DiaDiemService diaDiemService;

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
        model.addAttribute("city", new String());
        model.addAttribute("district", new String());
        model.addAttribute("ward", new String());
        model.addAttribute("sonha", new String());

        return "user/home/datve";
    }

    public String getLocation(String cityId, String districtId, String wardId, String sonha) {
        try {
            String jsonData = readJsonFromUrl(
                    "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json");
            JSONArray jsonArray = new JSONArray(jsonData);
            // Find city
            String cityname = "";
            String districtname = "";
            String wardname = "";

            JSONObject city = new JSONObject(findLocationById(jsonArray, cityId));
            if (city != null) {
                cityname = city.getString("Name");
                // Find district
                JSONArray districts = city.getJSONArray("Districts");
                JSONObject district = new JSONObject(findLocationById(districts, districtId));

                if (district != null) {
                    districtname = district.getString("Name");

                    // Find ward
                    JSONArray wards = district.getJSONArray("Wards");
                    JSONObject foundWard = new JSONObject(findLocationById(wards, wardId));

                    if (foundWard != null) {
                        wardname = foundWard.getString("Name");
                    } else {
                        System.out.println("Ward not found.");
                    }
                } else {
                    System.out.println("District not found.");
                }
            } else {
                System.out.println("City not found.");
            }
            return sonha + ", " + wardname + ", " + districtname + ", " + cityname;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String readJsonFromUrl(String url) throws IOException {
        try (Scanner scanner = new Scanner(new URL(url).openStream(),
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    private static Map<String, Object> findLocationById(JSONArray locations, String id) {
        return locations
                .toList()
                .stream()
                .filter(obj -> ((Map<String, Object>) obj).get("Id").equals(id))
                .findFirst()
                .map(obj -> (Map<String, Object>) obj)
                .orElse(null);
    }

    public DiaDiem getLatLong(String address) {
        DiaDiem result = new DiaDiem();
        try {
            String accessToken = "pk.eyJ1IjoiZWFsZmxtIiwiYSI6ImNsNmEyYTl0NTBjeXkzanFmajV1ZmFkcXEifQ.ajO7MarYAHOccS4yrY4cPg";

            String encodedAddress = URLEncoder.encode(address, "UTF-8");
            String apiUrl = "https://api.mapbox.com/geocoding/v5/mapbox.places/" + encodedAddress
                    + ".json?access_token=" + accessToken;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Optional: Set request method to GET
            connection.setRequestMethod("GET");

            // Get the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.toString());

            // Extract latitude and longitude
            JsonNode firstFeature = jsonNode.path("features").path(0);
            String latitude = firstFeature.path("center").path(1).asText();
            String longitude = firstFeature.path("center").path(0).asText();
            // System.out.println("Latitude: " + latitude);
            // System.out.println("Longitude: " + longitude);
            result.setLat(Double.parseDouble(latitude));
            result.setLng(Double.parseDouble(longitude));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/datve/{id}")
    public String datve(@PathVariable("id") Long id, @ModelAttribute("phieuDatVe") PhieuDatVe phieuDatVe,
            @ModelAttribute("soGheSpan") String soGheSpan,
            @ModelAttribute("city") String tinh, @ModelAttribute("district") String huyen,
            @ModelAttribute("ward") String xa, @ModelAttribute("sonha") String sonha,
            Authentication authentication) {
        List<String> list = new ArrayList<String>();
        if (soGheSpan.length() == 0 && phieuDatVe.getHangHoa() == null) {
            String s = "redirect:/khachhang/datve/" + id.toString();
            return s;
        }
        // ----- get user
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<KhachHang> kh = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getKhachHangs();
        KhachHang khachHang = kh.get(0);
        // ----- get chuyenxe
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        // ----set list pdv cho khachhang
        List<PhieuDatVe> pdvs = khachHang.getPhieuDatVes();

        phieuDatVe.setKhachHang(khachHang);
        // -----set inf cho chuyenXe
        phieuDatVe.setChuyenXe(chuyenXe);

        chuyenXe.setSoViTriConTrong(chuyenXe.getSoViTriConTrong() - list.size());
        chuyenXe.setHangHoaConTrong(chuyenXe.getHangHoaConTrong() - phieuDatVe.getHangHoa());
        // -----set inf phiếu đặt vé
        phieuDatVe.setNgayDat(LocalDate.now());
        phieuDatVe.setTinhTrangVe("Đã đặt");
        // ------set diaDiem
        DiaDiem diaDiem = new DiaDiem();
        if(tinh.equals("") && huyen.equals("") && xa.equals("") && sonha.equals(""))
            diaDiem=chuyenXe.getTuyen().getDiemDen();
            else 
                diaDiem = getLatLong(getLocation(tinh, huyen, xa, sonha));
        phieuDatVe.setDiaDiem(diaDiem);
        // ----- lưu

        diaDiemService.save(diaDiem);

        phieuDatVeService.save(phieuDatVe);

        khachHangService.save(khachHang);

        chuyenXeService.save(chuyenXe);

        if (soGheSpan.length() > 0) {
            soGheSpan += ", ";
            while (soGheSpan.length() > 0) {
                String temp = "";
                if (soGheSpan.charAt(3) == ',') {
                    temp += soGheSpan.substring(0, 3);
                    list.add(temp);
                    soGheSpan = soGheSpan.substring(5);
                } else if (soGheSpan.charAt(2) == ',') {
                    temp += soGheSpan.substring(0, 2);
                    list.add(temp);
                    soGheSpan = soGheSpan.substring(4);
                }
            }
            for (String s : list) {
            GheCuaChuyen ghe = gheCuaChuyenService.getGheCuaChuyenByChuyenGhe(s, id);
            ghe.setTrangThai(true);
            ghe.setPhieuDatVe(phieuDatVe);
            gheCuaChuyenService.save(ghe);
        }
        }

        return "redirect:/khachhang";
    }

    @GetMapping("/chitietve/{id}")
    public String chitietphieudatve(@PathVariable("id") Long id,Model model) {
        PhieuDatVe pdv = phieuDatVeService.getPhieuDatVeById(id);
        List<GheCuaChuyen> ghes = gheCuaChuyenService.getAllGheCuaVe(id);
        String s ="";
        double totalKH = 0.0;
        for(GheCuaChuyen l : ghes) {
            totalKH+= pdv.getChuyenXe().getGiaVe().getGiaHanhKhach();
            s+= l.getGhe().getMoTa()+", ";
        }
        double totalHH = 0.0;
        if(pdv.getHangHoa()!=null)
            totalHH = pdv.getHangHoa() * pdv.getChuyenXe().getGiaVe().getGiaHangHoa();
        model.addAttribute("pdv", pdv);
        model.addAttribute("ghes", s);
        model.addAttribute("totalKH", totalKH);
        model.addAttribute("totalHH", totalHH);
        model.addAttribute("total", totalHH+totalKH);
        model.addAttribute("danhgia", new String());


        return "user/home/chitietphieudatve";
    }
    @PostMapping("/chitietve/{id}")
    public String postchitietphieudatve(@PathVariable("id") Long id, @ModelAttribute("danhgia") String danhgia){
        PhieuDatVe phieuDatVe = phieuDatVeService.getPhieuDatVeById(id);
        phieuDatVe.setDanhGiaChuyenXe(danhgia);
         int check = 0;
        phieuDatVeService.save(phieuDatVe);
       
        return"redirect:/khachhang/xemvedadat"; 
    }
}
