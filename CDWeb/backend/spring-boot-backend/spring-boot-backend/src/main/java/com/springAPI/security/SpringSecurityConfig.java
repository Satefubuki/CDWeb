package com.springAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springAPI.security.jwt.JwtEntryPoint;
import com.springAPI.security.jwt.JwtFilter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@DependsOn("passwordEncoder")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    private JwtEntryPoint accessDenyHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

//    private String usersQuery = "select username, password from user where username=?";
//
//    private String rolesQuery = "select username, role from user where username=?";
    
    private String usersQuery;

    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
        // Mình comment phần dưới này vì chúng ta ko sử dụng DB nhé. Nếu các bạn sử dụng, bỏ comment và config query sao cho phù hợp. Các bạn có thể GG để tìm hiểu thêm */
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password, active from user where  username=?")
                .authoritiesByUsernameQuery("select username, role from user where username=?")
                .passwordEncoder(passwordEncoder);
       
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()
               // .antMatchers("/product/**").authenticated()
             //   .antMatchers("/product-type").authenticated()
                .antMatchers(HttpMethod.POST, "/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/product/**").hasAuthority("ADMIN")
                .anyRequest().permitAll()

                .and()
                .exceptionHandling().authenticationEntryPoint(accessDenyHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}