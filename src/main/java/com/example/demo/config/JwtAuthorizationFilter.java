package com.example.demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.JWTService;
import com.example.demo.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;
    private final JWTService jwtservice;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, ObjectMapper mapper,JWTService jwtservice) {
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
        this.jwtservice=jwtservice;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 Map<String, Object> errorDetails = new HashMap<>();

	        try {
	            String accessToken = jwtUtil.resolveToken(request);
	            if (accessToken == null ) {
	                filterChain.doFilter(request, response);
	                return;
	            }
	            System.out.println("token : "+accessToken);
	            Claims claims = jwtUtil.resolveClaims(request);

	            if(claims != null & jwtUtil.validateClaims(claims)){
	                String userName = claims.getSubject();
	                UserDetails userdetails=jwtservice.loadUserByUsername(userName);
	                Authentication authentication =
	                        new UsernamePasswordAuthenticationToken(userdetails,"",userdetails.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }

	        }catch (Exception e){
	            errorDetails.put("message", "Authentication Error");
	            errorDetails.put("details",e.getMessage());
	            response.setStatus(HttpStatus.FORBIDDEN.value());
	            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	            mapper.writeValue(response.getWriter(), errorDetails);

	        }
	        filterChain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}

}
