package com.ciesla.rentyourdreamscarrentalclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class RentYourDreamsCarRentalClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentYourDreamsCarRentalClientApplication.class, args);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		driverManagerDataSource.setUrl("jdbc:postgresql://ec2-79-125-110-209.eu-west-1.compute.amazonaws.com:5432/ddquepm3vrtem9?sslmode=require");
		driverManagerDataSource.setUsername("okdstfxkheocom");
		driverManagerDataSource.setPassword("0f2f9456e11f910a15fa6c073038894da178b8ea3cc31c8e67ab3aed0f2a516a");
		return driverManagerDataSource;
	}

	@Bean(name = "restTemplate")
    public RestTemplate getRestClient() {
	    RestTemplate restClient = new RestTemplate(
	            new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory())
        );
	    return restClient;
    }
}
