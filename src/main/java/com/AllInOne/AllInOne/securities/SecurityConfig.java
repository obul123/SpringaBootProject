package com.AllInOne.AllInOne.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.AllInOne.AllInOne.jwt.JwtConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserDetailsServiceClass serviceClass;
	
	public SecurityConfig(UserDetailsServiceClass serviceClass)
	{
		System.out.println("UserDetailsServiceClass constructor overloading");
		this.serviceClass=serviceClass;
	}
	
	@Autowired
	private JwtConfig config;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception
	{
		return security.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(registry -> {
					registry.requestMatchers("/accountManagement/**").hasRole("USER");
					registry.requestMatchers("/customerManagement/**").hasRole("ADMIN");
					registry.requestMatchers("/wetherManagement/**").permitAll();
					registry.requestMatchers("/userManagement/**").permitAll();
					registry.requestMatchers("/JwtManagement/**").permitAll();
					registry.anyRequest().authenticated();
				})
				
				.formLogin(formLogin -> formLogin.permitAll())
				.addFilterBefore(config, UsernamePasswordAuthenticationFilter.class)
				.build();	
	}
	                                                                                                                                                                                                                      
	@Bean
	public UserDetailsService userDetailsService()
	{
		return serviceClass;
	}
	
	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(serviceClass);
		authenticationProvider.setPasswordEncoder(encoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager()
	{
		return new ProviderManager(authenticationProvider());
	}
	
	

}
