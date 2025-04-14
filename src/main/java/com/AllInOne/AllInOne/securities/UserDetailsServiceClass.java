package com.AllInOne.AllInOne.securities;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.AllInOne.AllInOne.entities.Users1204;
import com.AllInOne.AllInOne.repositories.Users1204Repo;

@Configuration
public class UserDetailsServiceClass implements UserDetailsService{
	
	private final Users1204Repo repo;
	
	@Autowired
	public UserDetailsServiceClass(Users1204Repo repo)
	{
		System.out.println("constructor based dependency injection");
		this.repo=repo;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
		Optional<Users1204> users1204 = repo.findById(username);
		if(!users1204.isPresent())
			throw new UsernameNotFoundException("User not found with username - "+username);
		Users1204 user = users1204.get();
		
		UserDetails userSer = User.builder()
				 .username(username)
				 .password(encoder.encode(user.getPassword()) )
				 .roles(getRoles(user.getRoles()))
				 .build();
		
		return userSer;
	}
	
	
	
	public String[] getRoles(String roles)
	{
		return roles.split(",");
	}

}
