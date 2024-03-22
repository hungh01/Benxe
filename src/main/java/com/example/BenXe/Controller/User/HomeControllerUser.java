package com.example.BenXe.Controller.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BenXe.Controller.User.vnpay.Config;
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

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

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
            chuyenXe.setSoViTriConTrong(chuyenXe.getSoViTriConTrong() - list.size());
        }
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
        for (String s : list) {
            GheCuaChuyen ghe = gheCuaChuyenService.getGheCuaChuyenByChuyenGhe(s, id);
            ghe.setTrangThai(true);
            ghe.setPhieuDatVe(phieuDatVe);
            gheCuaChuyenService.save(ghe);
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
        model.addAttribute("dateNow", LocalDate.now());

        return "user/home/chitietphieudatve";
    }
    @PostMapping("/chitietve/{id}")
    public String postchitietphieudatve(@PathVariable("id") Long id, @ModelAttribute("danhgia") String danhgia){
        PhieuDatVe phieuDatVe = phieuDatVeService.getPhieuDatVeById(id);
        if(phieuDatVe.getChuyenXe().getNgayChay().isBefore(LocalDate.now())){
            phieuDatVe.setDanhGiaChuyenXe(danhgia);
            phieuDatVeService.save(phieuDatVe);
        }else{
            List<GheCuaChuyen> l = gheCuaChuyenService.getAllGheCuaVe(id);
            for(GheCuaChuyen ghe :l){
                ghe.setTrangThai(false);
                ghe.setPhieuDatVe(null);
                gheCuaChuyenService.save(ghe);
            }
            phieuDatVeService.delete(id);
        }
        return"redirect:/khachhang/xemvedadat"; 
    }
    @GetMapping("/payment-callback")
    public void paymentCallback(@RequestParam Map<String, String> queryParams,HttpServletResponse response) throws IOException {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        String contractId = queryParams.get("contractId");
        String registerServiceId = queryParams.get("registerServiceId");
        String billId = queryParams.get("billId");
        if(contractId!= null && !contractId.equals("")) {
            if ("00".equals(vnp_ResponseCode)) {
                // Giao dịch thành công
                // Thực hiện các xử lý cần thiết, ví dụ: cập nhật CSDL
            response.sendRedirect("https://benxemiendong.azurewebsites.net/xemvedadat");
            } else {
                // Giao dịch thất bại
                // Thực hiện các xử lý cần thiết, ví dụ: không cập nhật CSDL\
                response.sendRedirect("https://benxemiendong.azurewebsites.net/payment-failed");
                
            }
        }
        if(registerServiceId!= null && !registerServiceId.equals("")) {
            if ("00".equals(vnp_ResponseCode)) {
                // Giao dịch thành công
                // Thực hiện các xử lý cần thiết, ví dụ: cập nhật CSDL
                
            response.sendRedirect("https://benxemiendong.azurewebsites.net/khachhang/xemvedadat");
            } else {
                // Giao dịch thất bại
                // Thực hiện các xử lý cần thiết, ví dụ: không cập nhật CSDL\
                response.sendRedirect("https://benxemiendong.azurewebsites.net/payment-failed");
                
            }
        }
        if(billId!= null && !billId.equals("")) {
            if ("00".equals(vnp_ResponseCode)) {
                // Giao dịch thành công
                // Thực hiện các xử lý cần thiết, ví dụ: cập nhật CSDL
            response.sendRedirect("https://benxemiendong.azurewebsites.net/info-student");
            } else {
                // Giao dịch thất bại
                // Thực hiện các xử lý cần thiết, ví dụ: không cập nhật CSDL\
            response.sendRedirect("https://benxemiendong.azurewebsites.net/payment-failed");
            }
        }
    
    }
    @GetMapping("/pay")
	public void getPay(HttpServletResponse response, @PathVariable("id") Long id, @ModelAttribute("phieuDatVe") PhieuDatVe phieuDatVe,
            @ModelAttribute("soGheSpan") String soGheSpan,
            @ModelAttribute("city") String tinh, @ModelAttribute("district") String huyen,
            @ModelAttribute("ward") String xa, @ModelAttribute("sonha") String sonha) throws ServletException, IOException, UnsupportedEncodingException{


		String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = 10000*100;
        String bankCode = "NCB";
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = Config.vnp_TmnCode;
        
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl+"?contractId="+5);
        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);



        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        response.sendRedirect(paymentUrl);
	}
}
