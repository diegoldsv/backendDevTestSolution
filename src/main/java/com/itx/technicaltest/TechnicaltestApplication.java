package com.itx.technicaltest;

import com.itx.technicaltest.controller.SimilarProductsController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechnicaltestApplication {

	private final SimilarProductsController similarProductsController;

	public TechnicaltestApplication(SimilarProductsController similarProductsController)
	{
		this.similarProductsController = similarProductsController;
	}

	public static void main(String[] args) {
		SpringApplication.run(TechnicaltestApplication.class, args);
	}

}
