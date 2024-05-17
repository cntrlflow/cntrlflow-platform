package com.cntrlflow.server.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cntrlflow.server.utility.BasicAuthUtil;
import com.cntrlflow.server.utility.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Value("${cntrlflow.server.testing:false}")
	private Boolean testStatus;
	
	@Autowired
	private BasicAuthUtil basicAuthUtil;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.info("[cntrlflow] applying doFilterInternal");
			String currentPath = request.getRequestURI().toLowerCase();

			log.info("[cntrlflow] currentPath: " + currentPath);

			if(currentPath.equalsIgnoreCase("/api/auth/login")) {
				log.info("[cntrlflow] inside else if condition");
				String authorization = request.getHeader("Authorization");
				
				log.info("[cntrlflow] authorization: " + authorization);
				
				if (authorization.isEmpty() || authorization.isBlank()) {
					throw new RuntimeException("[cntrlflow] authorization is empty");
				}
				
				if(!authorization.equalsIgnoreCase(basicAuthUtil.getBasicToken())) {
					throw new RuntimeException("[cntrlflow] authorization is invalid");
				} else {
					log.info("[cntrlflow] basic token validated successfully.");
				}

				filterChain.doFilter(request, response);
			}

			else if (!currentPath.equalsIgnoreCase("/api/graphql")) {
				log.info("[cntrlflow] inside if condition");
				filterChain.doFilter(request, response);
			}
			
			else {
				log.info("[cntrlflow] inside else if condition: " + testStatus);
				
				if(!Boolean.valueOf(testStatus)) {
					String jwtToken = jwtUtil.getJwtFromCookie(request);
					String username = jwtUtil.getUsername(jwtToken);
					String authorization = request.getHeader("Authorization");

					log.info("[cntrlflow] jwtToken: " + jwtToken);
					log.info("[cntrlflow] authorization: " + authorization);

					if (authorization.isEmpty() || authorization.isBlank()) {
						throw new RuntimeException("[cntrlflow] authorization is empty");
					}

					if (username != null) {
						if (!StringUtils.hasText(jwtToken) && !jwtUtil.validateJwtToken(jwtToken)) {
							throw new RuntimeException("[cntrlflow] invalid token");
						}

						final UserDetails userDetails = new User(username, "", new ArrayList<>());
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						usernamePasswordAuthenticationToken = null;
					} else {
						throw new RuntimeException("[cntrlflow] invalid authentication");
					} 
					filterChain.doFilter(request, response);
				} else {
					log.info("[cntrlflow] inside else condition");
					filterChain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			log.error("[cntrlflow] exception in security filter: " + e.getMessage());
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
