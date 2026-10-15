package com.agarcia.examenes_microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan ({"com.agarcia.examenes_commons.models.entity"})
public class ExamenesMicroserviciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenesMicroserviciosApplication.class, args);
	}

}
