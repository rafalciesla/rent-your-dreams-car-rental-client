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
//        System.out.println(dataSourceUrl);
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		SpringApplication.run(RentYourDreamsCarRentalClientApplication.class, args);
	}

//	@Value("${spring.datasource.url}")
//    private String dataSourceUrl;
//
//    @Value("${spring.datasource.username}")
//    private String dataSourceUsername;
//
//    @Value("${spring.datasource.password}")
//    private String dataSourcePassword;

	@Bean(name = "restTemplate")
    public RestTemplate getRestClient() {
	    RestTemplate restClient = new RestTemplate(
	            new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory())
        );
	    return restClient;
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//	public DriverManagerDataSource dataSource() {
//	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
//	    driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/rentyourdreams");
//	    driverManagerDataSource.setUsername("postgres");
//	    driverManagerDataSource.setPassword("postgres");
//	    return driverManagerDataSource;
//    }
}
