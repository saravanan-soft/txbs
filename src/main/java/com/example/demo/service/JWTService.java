package com.example.demo.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.BankRoles;
import com.example.demo.entity.BankUsers;



@Service
public class JWTService implements UserDetailsService {

	private UserDao userrep;
	
	public JWTService(UserDao userrep) {
		this.userrep=userrep;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		BankUsers users=userrep.findById(username).orElseThrow(()->new UsernameNotFoundException("User Not Found with -> username or email: " + username));		
		
		
		
		UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(users.getUserId())
                        .password(users.getFirstPin()).roles("ADMIN")
                        .build();
	
	
        return userDetails;
	
	}
	
	private Set getAuthority(BankUsers users) {
		Set<SimpleGrantedAuthority> authorities=new HashSet<SimpleGrantedAuthority>();
		users.getRoleCode().forEach(role->authorities.add(new SimpleGrantedAuthority(role.getCode())));
		return authorities;
	}
	
	
	
	

		
	
	

}
