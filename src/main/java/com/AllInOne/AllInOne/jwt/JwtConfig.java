package com.AllInOne.AllInOne.jwt;

import java.io.IOException;

import org.apache.coyote.http11.HeadersTooLargeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.AllInOne.AllInOne.securities.UserDetailsServiceClass;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtConfig extends OncePerRequestFilter{
	
	@Autowired
	private JwtClass jwtClass;
	
	@Autowired
	private UserDetailsServiceClass serviceClassa;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String baseHeader = request.getHeader("Authorization");
		if(baseHeader==null)
		{
			filterChain.doFilter(request, response);
			return;
		}
		if(!baseHeader.startsWith("Bearer Token"))
			throw new HeadersTooLargeException("Invalid Header");
		
		String mainHeader = baseHeader.substring(12);
		if(jwtClass.isTOkenvalid(mainHeader) && SecurityContextHolder.getContext()==null)
		{
			String username = jwtClass.getUserName(mainHeader);
			UserDetails service= serviceClassa.loadUserByUsername(username);	
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(username, service.getPassword(), service.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);;
			filterChain.doFilter(request, response);
		}
		
		
	}

}
