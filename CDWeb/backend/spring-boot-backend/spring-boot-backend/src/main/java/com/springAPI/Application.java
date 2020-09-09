package com.springAPI;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springAPI.service.FilesStorageService;

@SpringBootApplication
public class Application implements CommandLineRunner{
//public class Application {
	@Autowired
	FilesStorageService filesStorageService;

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		filesStorageService.deleteAll();
//		filesStorageService.init();
	}


}

