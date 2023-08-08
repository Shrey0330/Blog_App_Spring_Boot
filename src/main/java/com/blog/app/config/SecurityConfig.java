package com.blog.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.app.security.CustomUserSecurity;
import com.blog.app.security.JwtAuthenEntry;
import com.blog.app.security.JwtAuthenticationFilter;


@Configuration

@EnableWebSecurity



public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustomUserSecurity customSecurity;
	@Autowired
	private JwtAuthenEntry jwtAuthenticationEntry;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(this.customSecurity).passwordEncoder(passwordEncoder());
		 
	}
@Override
	
	    public void configure(HttpSecurity http) throws Exception {
	        http
	        .csrf()
	        .disable()
	        .authorizeHttpRequests()
	        .antMatchers("/api/v1/login").permitAll()
	        .anyRequest()
	        .authenticated()
	        .and()
	        .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntry)
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        
	        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        
	      
	    }
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}


}


