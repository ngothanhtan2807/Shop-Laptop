package com.devpro.conf;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecureConf extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests() // thực hiện xác thực request ngưười dùng gửi lên.
                .antMatchers("/css/**", "/js/**", "/img/**", "/vendor/**"
                        , "/font-awesome/**", "/summernote/**", "/files/**", "/images/**", "/upload/**").permitAll()

                .antMatchers("/admin/**").hasAnyAuthority("admin")
                .anyRequest().authenticated()
                .and() // kết hợp với điều kiện.
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true) // xoá hết dữ liệu trên seesion
                .deleteCookies("JSESSIONID") // xoá hết dữ liệu trên cokies.
                .permitAll()

                .and() // kết hợp với điều kiện.

                .formLogin() // thực hiện xác thực qua form(username và password)
                .loginPage("/login") // trang login do mình thiết kế.
                .loginProcessingUrl("/perform_login") // link action for form post.
                .defaultSuccessUrl("/home", true) // when user success authenticated then go to this url.
                .failureUrl("/login?login_error=true") // nhập username, password sai thì redirect về trang nào.
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        return encoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
