package com.AllInOne.AllInOne.jwt;

import java.sql.Date;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.AllInOne.AllInOne.entities.Users12041;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtClass {
	
	private final String secret_key="d4XMhie2BU2PKFAneoFHnVr/aGIstOe0XFAsr8fOFMUenVpzBaXWUNCm/h+E31dXvSOeCRRDypm4vmbUvkvwPQ==";
	
	private final long timee = TimeUnit.MILLISECONDS.toMicros(10000);
	
	public SecretKey getSecretKey()
	{
		byte[] bytes = Base64.getDecoder().decode(secret_key);
		return Keys.hmacShaKeyFor(bytes);
	}
	
	public String generateJwtToken(Users12041 users12)
	{
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("obul", "obul");
		map.put("reddy", "obul");
		return Jwts.builder()
				.subject(users12.getUsername())
			    .issuedAt(Date.from(Instant.now()))
			    .expiration(Date.from(Instant.now().plusMillis(timee)))
			    .signWith(getSecretKey())
			    .claims(map)
			    .compact();
	}

	public boolean isTOkenvalid(String mainHeader) {
		Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(mainHeader).getPayload();
		if(claims.getExpiration().compareTo(Date.from(Instant.now()))<0)
			return true;
		return false;
	}

	public String getUserName(String mainHeader) {
		Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(mainHeader).getPayload();
		return claims.getSubject();
	}

}
