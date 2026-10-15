package com.agarcia.gateway_microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayMicroserviciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMicroserviciosApplication.class, args);
	}

}
