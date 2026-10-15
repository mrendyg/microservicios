package com.agarcia.curso_microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan({"com.agarcia.examenes_commons.models.entity",
				"com.agarcia.curso_microservicios.models.entity"})
public class CursoMicroserviciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoMicroserviciosApplication.class, args);
	}

}
 