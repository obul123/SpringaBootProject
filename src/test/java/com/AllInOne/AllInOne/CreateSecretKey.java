package com.AllInOne.AllInOne;


import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;

public class CreateSecretKey {
	
	@Test
	public void cretaeScretKey()
	{
		System.out.println("SECRET KEY GOT GENERETARED");
		
		SecretKey key= Jwts.SIG.HS512.key().build();
		
		String secretKey = DatatypeConverter.printBase64Binary(key.getEncoded());
		
		System.out.println("secret key is "+secretKey);
	}

}
