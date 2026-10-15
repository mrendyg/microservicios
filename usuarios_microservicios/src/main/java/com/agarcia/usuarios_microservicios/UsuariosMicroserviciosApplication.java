package com.agarcia.usuarios_microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients 
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = {"com.agarcia.alumnos_commons.models.entity"})
public class UsuariosMicroserviciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosMicroserviciosApplication.class, args);
	}

}