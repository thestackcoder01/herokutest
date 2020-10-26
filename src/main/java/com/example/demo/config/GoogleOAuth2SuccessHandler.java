package com.example.demo.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.repositary.UserRepositary;



@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{
      
	@Autowired
	UserRepositary userRepositary;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException{
		   OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		   String email = token.getPrincipal().getAttributes().get("email").toString();
		   if(userRepositary.findByEmail(email).isEmpty()) {
			   User user = new User();
			   user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			   user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
			   user.setEmail(email);
			   userRepositary.save(user);
			   redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse,"/welcome");
		   }
	}
}
