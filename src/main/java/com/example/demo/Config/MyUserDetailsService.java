package com.example.demo.Config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;
import java.util.Optional;

import com.example.demo.model.Role;
import com.example.demo.model.UserS;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserSRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository repoRole;

    private final UserSRepository repou;

    static Logger log = LoggerFactory.getLogger(MyUserDetailsService.class.getName());

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserS u  = null;
        for(int i=0;i<repou.findAll().size();i++){
            if (Objects.equals(repou.findAll().get(i).getEmailNumber(), email)) {
                //u = new UserS();
                u = repou.findAll().get(i);
                break;
            }
        }
        String role = "";
        Optional<Role> ORole = repoRole.findById(u.getIdRole());
        log.info(u.getIdRole().toString());
        if (!ORole.isPresent())
            return null;
        role = ORole.get().getName();
        return User.withUsername(u.getEmailNumber()).password(passwordEncoder.encode(u.getPassword())).roles(role)
                .build();
    }
}
