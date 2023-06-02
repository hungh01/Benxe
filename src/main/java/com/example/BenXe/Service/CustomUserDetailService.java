package com.example.BenXe.Service;

import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Model.KhachHang;
import com.example.BenXe.Model.TaiKhoan;

import com.example.BenXe.Repository.IKhachHangRepository;
import com.example.BenXe.Repository.ITaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private ITaiKhoanRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan user = userRepository.findByUsername(username);
        if (user == null )
            throw new UsernameNotFoundException("User not found");
        List<KhachHang> khachHangs= user.getKhachHangs();
        var i = 1;
        return new CustomTaiKhoanDetail(user, userRepository);
    }

}

