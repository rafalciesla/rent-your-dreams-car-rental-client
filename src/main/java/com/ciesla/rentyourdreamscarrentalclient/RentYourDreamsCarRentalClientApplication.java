package com.ciesla.rentyourdreamscarrentalclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RentYourDreamsCarRentalClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentYourDreamsCarRentalClientApplication.class, args);
	}

	@Bean(name = "restTemplate")
    public RestTemplate getRestClient() {
	    RestTemplate restClient = new RestTemplate(
	            new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory())
        );
	    return restClient;
    }
}
