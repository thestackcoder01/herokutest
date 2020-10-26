package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.User;
import com.example.demo.repositary.UserRepositary;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{
   @Autowired
   UserRepositary userRepository;
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
		Optional<User> optionalUser= userRepository.findByEmail(s);
		optionalUser.orElseThrow( () -> new UsernameNotFoundException("nahi mila user"));
		 return optionalUser.map(CustomUserDetail::new).get();
	}
}
