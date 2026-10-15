package com.agarcia.respuestas_microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableAutoConfiguration (exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients 
@EnableDiscoveryClient
@SpringBootApplication
public class RespuestasMicroserviciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RespuestasMicroserviciosApplication.class, args);
	}

}
