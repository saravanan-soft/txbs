package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties 
public class TxbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxbsApplication.class, args);
	}

}
