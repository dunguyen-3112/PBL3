package com.example.demo.security;

import com.example.demo.model.Nhanvien;
import com.example.demo.service.NhanVienService;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final NhanVienService nhanVienService;

    public MyUserDetailsService(PasswordEncoder passwordEncoder, NhanVienService nhanVienService) {
        this.passwordEncoder = passwordEncoder;
        this.nhanVienService = nhanVienService;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Nhanvien nhanvien = nhanVienService.findByemail(email);
        if(nhanvien == null)
        {
            throw new UsernameNotFoundException("The email: "+ email + "not found.");
        }
        String role = nhanvien.getMaChucVu().toString();
        return User.withUsername(nhanvien.getEmail()).password(passwordEncoder.encode(nhanvien.getPassword())).roles(role).build();
    }
}
