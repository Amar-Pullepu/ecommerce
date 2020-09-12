package com.ecommerce.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ecommerce"})
@EnableJpaRepositories(basePackages = {"com.ecommerce"})
@EntityScan(basePackages = {"com.ecommerce"})
@PropertySource(value = { "Controller-application.properties", "Models-application.properties", "Web-application.properties" })
public class WebApplication{
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}