package com.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.app.entity.Product;
import com.app.repository.ProductRepository;


@SpringBootApplication
public class SpringbootCrudStudentApplication implements CommandLineRunner{

	@Autowired
	private ProductRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudStudentApplication.class, args);
	}

	 @Override
	    public void run(String... args) throws Exception {

	    }

}
