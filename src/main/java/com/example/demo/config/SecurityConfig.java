package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.service.JWTService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private JWTService jwtService;
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**"
    };

	public SecurityConfig(JWTService jwtService,JwtAuthorizationFilter jwtAuthorizationFilter) {
		this.jwtService = jwtService;
		this.jwtAuthorizationFilter= jwtAuthorizationFilter;
	}
	
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http,BCryptPasswordEncoder noOpPasswordEncoder) throws Exception {
		
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http.cors().and().csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/rest/auth/**").permitAll().antMatchers(AUTH_WHITE_LIST).permitAll().antMatchers("/api/v1/saveusers/**").permitAll()
	                .anyRequest().authenticated()
	                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and().addFilterBefore(jwtAuthorizationFilter,UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }  
	  
	  @Bean
	  public WebMvcConfigurer corsConfigurer() {
	      return new WebMvcConfigurer() {
	          @Override
	          public void addCorsMappings(CorsRegistry registry) {

	              registry.addMapping("/**")
	                      .allowedOrigins("*")
	                      .allowedMethods("*")
	                      .allowedHeaders("*");

	          }
	      };
	  }
	  
	 
}
