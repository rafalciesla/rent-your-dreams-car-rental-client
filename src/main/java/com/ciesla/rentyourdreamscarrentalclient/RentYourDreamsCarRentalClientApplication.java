package com.ciesla.rentyourdreamscarrentalclient;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.username}")
	private String dataSourceUsername;

	@Value("${spring.datasource.password}")
	private String dataSourcePassword;

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(driverClassName);
		driverManagerDataSource.setUrl(dataSourceUrl);
		driverManagerDataSource.setUsername(dataSourceUsername);
		driverManagerDataSource.setPassword(dataSourcePassword);
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
