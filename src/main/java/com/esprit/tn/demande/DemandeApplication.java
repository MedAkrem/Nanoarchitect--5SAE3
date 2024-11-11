package com.esprit.tn.demande;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.esprit.tn.demande.AppResouces.Repository")
public class DemandeApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemandeApplication.class, args);
	}
}


