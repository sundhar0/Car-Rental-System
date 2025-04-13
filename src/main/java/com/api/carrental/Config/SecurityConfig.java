package com.api.carrental.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.carrental.Service.MyUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf->csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/userLogin/signup").permitAll()
				.requestMatchers("/api/userLogin/login").permitAll()
				.requestMatchers("/api/userLogin/token/generate").permitAll()
				.requestMatchers("/api/userLogin/userDetails").authenticated()
				.requestMatchers("/api/customer/hello").permitAll()
				.requestMatchers("/api/customer/add").permitAll()
				.requestMatchers("/api/customer/all").permitAll()
				.requestMatchers("/api/customer/one/{id}").permitAll()
				.requestMatchers("/api/customer/update/{id}").permitAll()
				.requestMatchers("/api/customer/search").permitAll()
				.requestMatchers("/api/customer/delete/{id}").permitAll()
				.requestMatchers("/api/carapproval/add/{carId}/{managerId}").hasAuthority("Manager")
				.requestMatchers("/api/Manager/getAll").hasAuthority("Manager")
				.anyRequest().authenticated()
			)
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			;

		return http.build();
	}
	@Bean
	AuthenticationProvider getAuth() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passEncoder());
		dao.setUserDetailsService(myUserService);	
		return dao;
	}
	@Bean
	BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
		  return auth.getAuthenticationManager();
	 }
}

