package com.springAPI.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springAPI.dto.UserDTO;
import com.springAPI.entity.UserEntity;
import com.springAPI.responsitory.UserRepository;
import com.springAPI.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    
   

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getToken(httpServletRequest);
        System.out.println(jwt);
        if (jwt != null && jwtProvider.validate(jwt)) {
            try {
            	System.out.println("da vao");
                String userAccount = jwtProvider.getUserAccount(jwt);
            	System.out.println("account: " + userAccount);
                UserEntity user = userRepository.findByUserName(userAccount);
                System.out.println(user.getUserName());
                // pwd not necessary
                // if jwt ok, then authenticate
                SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole());
                ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
                list.add(sga);
                UsernamePasswordAuthenticationToken auth
                        = new UsernamePasswordAuthenticationToken(user.getUserName(), null, list);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                logger.error("Set Authentication from JWT failed");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}
