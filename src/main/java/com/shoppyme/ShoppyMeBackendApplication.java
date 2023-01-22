package com.shoppyme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shoppyme.service.ProductService;

@SpringBootApplication
public class ShoppyMeBackendApplication {
	
	@Autowired
	public ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ShoppyMeBackendApplication.class, args);
	}

}
