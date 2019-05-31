package com.helc.complain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${auth.server.host}")
	private String authServerHost;
	
	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Bean
	public ResourceServerTokenServices tokenService() {
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setClientId(clientId);
		tokenServices.setClientSecret(clientSecret);
		tokenServices.setCheckTokenEndpointUrl(authServerHost + "/oauth/check_token");
		return tokenServices;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
		authenticationManager.setTokenServices(tokenService());
		return authenticationManager;
	}

}
