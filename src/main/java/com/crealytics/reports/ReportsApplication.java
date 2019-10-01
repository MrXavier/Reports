package com.crealytics.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.crealytics.reports.service", "com.crealytics.reports.controller"})
@EntityScan(basePackages = "com.crealytics.reports.model")
@EnableJpaRepositories(basePackages = "com.crealytics.reports.repository")
@EnableAutoConfiguration
public class ReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsApplication.class, args);
	}

}
