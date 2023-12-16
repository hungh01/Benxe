package com.example.BenXe.Controller.Xe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.weaver.ast.Call;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import com.example.BenXe.Model.DiaDiem;
import com.example.BenXe.Model.Ghe;
import com.example.BenXe.Model.GheCuaChuyen;
import com.example.BenXe.Model.HoaDon;
import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Model.PhieuDatVe;
import com.example.BenXe.Model.Xe;
import com.example.BenXe.Repository.IPhieuDatVeRepository;
import com.example.BenXe.Service.ChuyenXeService;
import com.example.BenXe.Service.GheCuaChuyenService;
import com.example.BenXe.Service.GheService;
import com.example.BenXe.Service.HoaDonService;
import com.example.BenXe.Service.PhieuDatVeService;
import com.example.BenXe.Service.TaiKhoanService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    @Autowired
    private PhieuDatVeService phieuDatVeService;

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

        List<Ghe> ghes = gheService.getAllGhes();

        chuyen.setHangHoaConTrong(xe.getHangHoa());
        chuyen.setSoViTriConTrong(xe.getSoGhe());
        chuyen.setXe(xe);
        chuyen.setTuyen(pdkt.getTuyen());
        chuyen.setLoaiXe(xe.getLoaiXe());
        chuyen.setGiaVe(pdkt.getGiaVe());

        chuyenXeService.save(chuyen);

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

    private double[][] graph; // Đồ thị
    private int numVertices; // Số lượng đỉnh
    private boolean[] visited; // Đánh dấu đỉnh đã thăm
    private int[] path; // Đường đi ngắn nhất
    private int minCost; // Chi phí ngắn nhất

    @GetMapping("/chuyendi/{id}")
    public String chuyendi(@PathVariable("id") Long id,Model model) {
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        List<PhieuDatVe> lstTC = phieuDatVeService.getPhieuDatVeByChuyenXeId(id);
        for(int i = 0; i < lstTC.size(); i++) {
            if(lstTC.get(i).getDiaDiem()==chuyenXe.getTuyen().getDiemDen()){
                lstTC.remove(i);
            }
        }
        // ObjectMapper objectMapper = new ObjectMapper();
        // String json = objectMapper.writeValueAsString(lstTC);
        model.addAttribute("lstTC", lstTC);
        model.addAttribute("diadiem", chuyenXe.getTuyen().getDiemDen());
        int k =0;
        return "xe/home/chuyendi";
    }
    @Value("${mapbox.accessToken}")
    private String mapboxAccessToken;

    public double getTravelTime(double lon1, double lat1, double lon2, double lat2) {
        try {
            String coordinates = Double.toString(lon1)+","+Double.toString(lat1)+";"+Double.toString(lon2)+","+Double.toString(lat2);
            String accessToken = mapboxAccessToken;

            String apiUrl = "https://api.mapbox.com/directions/v5/mapbox/driving/"
                    + coordinates + "?access_token=" + accessToken;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up the request
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Extract distance information
                JSONArray routes = jsonResponse.getJSONArray("routes");
                if (routes.length() > 0) {
                    JSONObject route = routes.getJSONObject(0);
                    double distance = route.getDouble("distance");

                    System.out.println("Total distance: " + distance + " meters");
                    return distance;
                } else {
                    System.out.println("No routes found.");
                }
            } else {
                // Handle error responses
                System.out.println("Error: " + responseCode);
            }
            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
