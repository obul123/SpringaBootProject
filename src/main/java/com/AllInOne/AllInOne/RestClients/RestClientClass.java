package com.AllInOne.AllInOne.RestClients;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientClass {
	
	@Bean
	public RestTemplate getRestClientClass()
	{
		return new RestTemplate();
	}

}
