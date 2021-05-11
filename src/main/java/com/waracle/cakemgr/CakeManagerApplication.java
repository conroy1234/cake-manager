package com.waracle.cakemgr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

@SpringBootApplication
public class CakeManagerApplication implements CommandLineRunner {

	@Autowired
	private CakeService cakeService;

	public static void main(String[] args) {
		SpringApplication.run(CakeManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CakeEntity> cakes = cakeService.findAllCakesFromCjonFile();

		cakes.forEach(cake -> {
			cakeService.createNewCake(cake);
		});

	}

}
