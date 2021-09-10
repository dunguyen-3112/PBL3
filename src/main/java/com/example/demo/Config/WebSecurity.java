package com.example.demo.Config;

import com.example.demo.model.Role;
import com.example.demo.model.UserS;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserSRepository;

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
    CommandLineRunner commandLineRunner(UserSRepository nhanvienRepository,RoleRepository repoRole){
        return args->{
            Role cv = null;
            UserS admin = new UserS();
            try{
                cv = repoRole.findByName("admin");
                admin = nhanvienRepository.findById(100000000L).get();
            }
            catch (Exception e)
            {
                cv = new Role();
                cv.setName("admin");
                admin.setName("admin");
                admin.setAddress("Việt Nam");
                admin.setCode("111111111");
                admin.setGender(true);
                admin.setEmailNumber("admin@gmail.com");
                admin.setBirthdate(new Date(201, 11, 31));
                admin.setPassword("123@Abc");
                admin.setIdRole(repoRole.save(cv).getId());
                nhanvienRepository.save(admin);
            }
        };
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**","/image/**","/webjars/**","/qltv/0/theloais**","/qltv/0/**","/js/index.js").permitAll()
                .and().authorizeRequests()
                .antMatchers("/qltv/1/lops/**","/qltv/1/khoas/**","/qltv/1/theloais/**",
                "/qltv/1/sachs/**","/qltv/1/sinhviens/**","/qltv/1/dkmuontras/**","/qltv/1").hasAnyRole("admin","user")
                .antMatchers("/qltv/**").hasAnyRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/qltv/1",true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/qltv/0");
    }
}
