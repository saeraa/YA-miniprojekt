package com.example.commonbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final KeycloakLogoutHandler keycloakLogoutHandler;

	public WebSecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {
		this.keycloakLogoutHandler = keycloakLogoutHandler;
	}

	@Bean
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http
				//.cors()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/orders")
//				.authenticated()
//				.anyRequest()
//				.permitAll();
				//.hasRole("USER")
				//.anyRequest()
				.anonymous().anyRequest()
				.permitAll();
//		http.oauth2Login()
//				.and()
//				.logout()
//				.addLogoutHandler(keycloakLogoutHandler)
//				.logoutSuccessUrl("/");
//		http
//				//.cors().disable()
//				.csrf().disable()
//				.authorizeHttpRequests()
//				//.antMatchers("/**").hasRole("USER")
//				.antMatchers("/**").permitAll()
//				.and()
//				.httpBasic();
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService () {
		UserDetails user = User.withUsername("sara")
				.password("{noop}password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}