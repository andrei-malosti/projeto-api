package com.projeto.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjetoLoja1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLoja1Application.class, args);
	}

}
