package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
  @Autowired
   CustomUserDetailsService customUserDetailsService;
  
  @Autowired
  UserDetailsService userDetailsService;
  @Autowired
  GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http 
		       .authorizeRequests()
		       .antMatchers("/" ,"/register","/h2-console/**").permitAll()
		       .anyRequest()
		       .authenticated()
		       .and()
		       .formLogin()
		       .loginPage("/login")
		       .permitAll()
		       .failureUrl("/login?error=true")
		       .defaultSuccessUrl("/welcome")
		       .usernameParameter("email")
		       .passwordParameter("password")
		       .and()
		       .oauth2Login()
		       .loginPage("/login")
		       .successHandler(googleOAuth2SuccessHandler)
		       .and()
		       .logout()
		       .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		       .logoutSuccessUrl("/")
		       .invalidateHttpSession(true)
		       .deleteCookies("JSESSIONID")
		       .and()
		       .exceptionHandling()
		       .and()
		       .csrf()
		       .disable();
		http.headers().frameOptions().disable();
    }
	
	@Bean
	public BCryptPasswordEncoder bcPasswordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailsService);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		 web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**" , "/images/**");
	}
}
