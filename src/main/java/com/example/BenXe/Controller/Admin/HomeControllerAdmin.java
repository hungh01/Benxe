package com.example.BenXe.Controller.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.example.BenXe.Model.DiaDiem;
import com.example.BenXe.Model.GiaVe;
import com.example.BenXe.Model.LoaiTK;
import com.example.BenXe.Model.LoaiXe;
import com.example.BenXe.Model.NhanVien;
import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Model.Tuyen;
import com.example.BenXe.Service.BaiDauXeService;
import com.example.BenXe.Service.DiaDiemService;
import com.example.BenXe.Service.GiaVeService;
import com.example.BenXe.Service.LoaiTKService;
import com.example.BenXe.Service.LoaiXeService;
import com.example.BenXe.Service.NhanVienService;
import com.example.BenXe.Service.PhieuDatVeService;
import com.example.BenXe.Service.TaiKhoanService;
import com.example.BenXe.Service.TuyenService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private PhieuDatVeService phieuDatVeService;
    @Autowired
    private BaiDauXeService baiDauXeService;
    @Autowired
    private GiaVeService giaVeService;
    @Autowired
    private TuyenService tuyenService;
    @Autowired
    private LoaiXeService loaiXeService;
    @Autowired
    private DiaDiemService diaDiemService;

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

        model.addAttribute("city1",new String());
        model.addAttribute("district1",new String());
        model.addAttribute("ward1",new String());
        model.addAttribute("detail1",new String());

        model.addAttribute("city2",new String());
        model.addAttribute("district2",new String());
        model.addAttribute("ward2",new String());
        model.addAttribute("detail2",new String());


        return "admin/createtuyen";
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
            result.setDiaDiem(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @PostMapping("createtuyen")
    public String createtuyen(@ModelAttribute("tuyen") Tuyen tuyen,
    @ModelAttribute("city1") String city1, @ModelAttribute("district1") String district1, @ModelAttribute("ward1") String ward1, @ModelAttribute("detail1") String detail1,
    @ModelAttribute("city2") String city2, @ModelAttribute("district2") String district2, @ModelAttribute("ward2") String ward2, @ModelAttribute("detail2") String detail2
    ){
        DiaDiem diaDiem1 = getLatLong(getLocation(city1, district1, ward1, detail1));
        DiaDiem diaDiem2 = getLatLong(getLocation(city2, district2, ward2, detail2));
        diaDiemService.save(diaDiem1);
        diaDiemService.save(diaDiem2);
        tuyen.setDiemDi(diaDiem1);
        tuyen.setDiemDen(diaDiem2);
        tuyenService.save(tuyen);
        for(LoaiXe loaiXe : loaiXeService.getAllLoaiXes()){
            if(loaiXe.getTenLoaiXe().equals("Base")){
                GiaVe gv = new GiaVe();
                gv.setLoaiXe(loaiXe);
                gv.setTuyen(tuyen);
                gv.setGiaHanhKhach(0D);
                gv.setGiaHangHoa(0D);
                giaVeService.save(gv);
            }
                
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
        giaVe1.setGiaHanhKhach(giaVe.getGiaHanhKhach());
        giaVe1.setGiaHangHoa(giaVe.getGiaHangHoa());
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
