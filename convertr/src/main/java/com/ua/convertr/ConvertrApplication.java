package com.ua.convertr;

import com.ua.convertr.service.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ConvertrApplication {

	@Resource
	FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(ConvertrApplication.class, args);
	}

}
