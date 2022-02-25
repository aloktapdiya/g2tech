package com.course5i.base.framework.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BasicAuthEntryPoint authEntryPoint;

	
	  @Bean public PasswordEncoder passwordEncoder() {
	  System.out.println("after BCryptPasswordEncoder");
	  
	  return new BCryptPasswordEncoder();
	  
	  // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	  
	  }

	//@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		try {
			
			System.out.println("before BasicAuthSecurityConfig2");
			String paascode = passwordEncoder().encode("user");

			UserDetails user = User.builder()
					.username("user")
					.password(paascode)
					.roles("USER")
					.build();
			
			
			
			auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
			 .withUser("user")
			 
			 .password("{bcrypt}user")
			 //.password(paascode) 
			 .roles("USER")
			 .credentialsExpired(true)
             .accountExpired(true)
             .accountLocked(true)
             .authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES");
			//auth.inMemoryAuthentication().withUser("user").password("userPass").roles("USER");
			//auth.inMemoryAuthentication().withUser("user").password("user");
			System.out.println("ssss---sss"+new BCryptPasswordEncoder().encode("user"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
		}

		// .authorities("ROLE_USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("before BasicAuthSecurityConfig3");

		http.csrf().disable().authorizeRequests()
		.antMatchers("/securityNone").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.authenticationEntryPoint(authEntryPoint);
		//.authenticationEntryPoint((request, response, exception) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED));
		System.out.println("before BasicAuthSecurityConfig4");

		//http.addFilterAfter(new AuthenticationFilter(), BasicAuthenticationFilter.class);
	}

	
	
	
	 
	/*
	 * @Bean public static NoOpPasswordEncoder passwordEncoder() { return
	 * (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); }
	 */			/*
			 * @Bean public PasswordEncoder delegatingPasswordEncoder() { Map<String,
			 * PasswordEncoder> encoders = new HashMap<>(); encoders.put("bcrypt", new
			 * BCryptPasswordEncoder()); DelegatingPasswordEncoder passwordEncoder = new
			 * DelegatingPasswordEncoder("bcrypt", encoders);
			 * passwordEncoder.setDefaultPasswordEncoderForMatches(new
			 * BCryptPasswordEncoder()); return passwordEncoder; }
			 */
	/*
	 * public void addResourceHandlers(ResourceHandlerRegistry registry) { registry
	 * .addResourceHandler("swagger-ui.html")
	 * .addResourceLocations("classpath:/META-INF/resources/"); registry
	 * .addResourceHandler("/webjars/**")
	 * .addResourceLocations("classpath:/META-INF/resources/webjars/"); }
	 */
}
