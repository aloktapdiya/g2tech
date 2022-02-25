package com.course5i.base.framework.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class BasicAuthEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		System.out.println("before BasicAuthEntryPoint1");
		System.out.println(new BCryptPasswordEncoder().encode("1"));

		response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		//response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
		 PrintWriter writer = response.getWriter();
		 writer.println(HttpStatus.UNAUTHORIZED.value());
	        writer.println("HTTP Status 401 - " + authException.getMessage());
			 writer.println(HttpStatus.UNAUTHORIZED.getReasonPhrase());

			System.out.println("after BasicAuthEntryPoint1"+writer);

	}

	@Override
	public void afterPropertiesSet() {
		setRealmName("Base-Framework");
		super.afterPropertiesSet();
		
	}
}
