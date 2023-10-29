package com.example.BenXe.Controller.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BenXe.Model.DiaDiem;

@RestController
@RequestMapping("/api")
public class LocationController {
    @PostMapping("/saveLocation")
    public ResponseEntity<?> saveLocation(@RequestBody DiaDiem data) {
        // Xử lý và lưu trữ dữ liệu từ trình duyệt
        // data.getCoordinates() và data.getPlaceName() chứa dữ liệu tọa độ và tên địa điểm
        // ...
        return ResponseEntity.ok("Dữ liệu đã được lưu trữ thành công.");
    }
}

