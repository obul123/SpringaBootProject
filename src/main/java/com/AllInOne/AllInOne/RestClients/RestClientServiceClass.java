package com.AllInOne.AllInOne.RestClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.AllInOne.AllInOne.entities.Users1204;
import com.AllInOne.AllInOne.entities.WhehterRoot;

@Service
public class RestClientServiceClass {
	
	@Autowired
	private RestClientClass clientClass ;
	
	private static final String api_key="30a9aaa4cfd28bb885915e5da4076555";
	
	public WhehterRoot getWhetherDetails(double latitude , double longitude)
	{
		String url = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid="+api_key;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("accept", "*/*");
		HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
		ResponseEntity<WhehterRoot> response = clientClass.getRestClientClass().exchange(url, HttpMethod.GET,
				httpEntity, WhehterRoot.class,new Object[0]);
		
		return response.getBody();
	}

	
	
	

}
