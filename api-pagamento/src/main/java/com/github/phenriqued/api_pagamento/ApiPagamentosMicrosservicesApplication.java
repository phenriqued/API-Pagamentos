package com.github.phenriqued.api_pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiPagamentosMicrosservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPagamentosMicrosservicesApplication.class, args);
	}

}
