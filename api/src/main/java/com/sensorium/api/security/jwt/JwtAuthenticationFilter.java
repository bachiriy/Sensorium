package com.sensorium.api.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sensorium.api.security.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Value("${jwt.header.string}")
	public String HEADER_STRING;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader("Cookie");
		String username = null;
		String authToken = null;

		logger.warn(header);

		if (header != null && header.startsWith("Authorization")) {
			authToken = header.substring(14);
			logger.debug("Processing token: " + authToken);

			try {
				username = jwtTokenUtil.extractUsername(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("Error occurred while retrieving Username from Token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("The token has expired", e);
			} catch (Exception e) {
				logger.error("Authentication Failed: " + e.getMessage(), e);
			}
		} else {
			logger.warn("Bearer string not found, ignoring the header");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				logger.info("User authenticated: " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(req, res);
	}

}
