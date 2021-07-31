package com.example.demo.Config;

import com.example.demo.service.ChucVuService;
import com.example.demo.service.NhanVienService;
import com.example.demo.model.Chucvu;
import com.example.demo.model.Nhanvien;
import com.example.demo.repository.ChucvuRepository;
import com.example.demo.repository.NhanvienRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.sql.Date;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    CommandLineRunner commandLineRunner(NhanvienRepository nhanvienRepository,ChucvuRepository chucvuRepository){
        return args->{
            Chucvu cv = new Chucvu();

            Nhanvien admin = new Nhanvien();
            try{
                cv = chucvuRepository.findById(2L).get();
                admin = nhanvienRepository.findById(100000000L).get();
            }
            catch (Exception e)
            {
                cv.setMaChucVu(2L);
                cv.setTenChucVu("Quản Lý");
                chucvuRepository.save(cv);
                admin.setTenNV("admin");
                admin.setAddress("address");
                admin.setCccd("123984128");
                admin.setGender(1);
                admin.setEmail("admin@gmail.com");
                admin.setMaChucVu(2L);
                admin.setNgaySinh(new Date(2001,12,31));
                admin.setMaNV(100000000L);
                admin.setPassword("12345@Va");
                nhanvienRepository.save(admin);
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/css/**","/js/**","/image/**","/webjars/**").permitAll()
                .and().authorizeRequests()
                .antMatchers("/qltv/sachs/**","/qltv/sinhviens/**","/qltv/khoas/**",
                        "/qltv/lops/**","/qltv/theloais/**","/qltv/dkmuontras/**").hasAnyRole("1","2")
                .antMatchers("/qltv/**").hasAnyRole("2")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/",true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
    }
}
