package com.cntrlflow.server.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> cors.configurationSource(request -> {
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowedOrigins(Collections.singletonList(request.getHeader("Origin")));
			configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
			configuration.setAllowedHeaders(Arrays.asList("Authorization", "X-Auth-Token",
					"Access-Control-Allow-Headers", "X-Requested-With", "X-HTTP-Method-Override", "Content-Type",
					"Accept", "Pragma", "Expires", "X-Frame-Options", "Cache-Control", "X-XSRF-TOKEN"));
			configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
			configuration.setAllowCredentials(true);
			configuration.setMaxAge(3600L);
			return configuration;
		}));

		http.csrf(csrf -> csrf.ignoringRequestMatchers("/", "/static/**", "/manifest.json/**",
				"/favicon.ico/**", "/robots.txt/**", "/*.xml", "/*.json", "/*.jpg", "/*.gif", "/*.svg",
				"/*.png", "/api/graphql", "/api/auth/**").csrfTokenRepository(getCsrfTokenRepository()));

		http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
				.requestMatchers("/api/auth/**", "/graphiql/**").permitAll()
				.requestMatchers("/api/graphql").authenticated()
				.anyRequest().authenticated());
		
		log.info("[cntrlflow] applying security filter");
		http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/", "/**", "/static/**", "/manifest.json/**",
				"/favicon.ico/**", "/robots.txt/**", "/api/login", "/*.xml", "/*.json", "/*.jpg", "/*.gif",
				"/*.png", "/*.jpg", "/*.html", "/*.css", "/*.js");
	}

	private CsrfTokenRepository getCsrfTokenRepository() {
		CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
		tokenRepository.setCookiePath("/");
		return tokenRepository;
	}
}