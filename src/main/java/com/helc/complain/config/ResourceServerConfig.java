package com.helc.complain.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		List<String> allowedOrigins = new ArrayList<>();
		allowedOrigins.add("*");

		List<String> allowedMethods = new ArrayList<>();
		allowedMethods.add("GET");
		allowedMethods.add("POST");
		allowedMethods.add("PUT");
		allowedMethods.add("DELETE");
		allowedMethods.add("OPTIONS");

		List<String> allowedHeaders = new ArrayList<>();
		allowedHeaders.add("*");

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(allowedOrigins);
		config.setAllowedMethods(allowedMethods);
		config.setAllowCredentials(true);
		config.setAllowedHeaders(allowedHeaders);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> filterCors = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		filterCors.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filterCors;
	}

}
