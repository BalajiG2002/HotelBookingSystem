package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers("/hotels/viewall").permitAll()
				.requestMatchers("/rooms/getbyid/{id}","/room/add/{hotelId}").hasAnyRole("USER","ADMIN")
				.requestMatchers("/Booking/add/{roomId}/{customerId}","/booking/delete/{id}").hasAnyRole("USER")
				.requestMatchers("/customer/add","/hostel/{hotelId}/rooms","/hotels/viewall").permitAll()
				.requestMatchers("/hotel/update/{id}","/hotel/delete/{id}","/hostel/add").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				)
			.httpBasic();
			return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("user")
							.password("{noop}password")
							.roles("USER")
							.build();
		UserDetails admin = User.withUsername("admin")
							.password("{noop}admin123")
							.roles("ADMIN")
							.build();
		return new InMemoryUserDetailsManager(user,admin);
	}
	
				
				
}

