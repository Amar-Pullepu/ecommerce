package com.ecommerce.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(getPasswdEncoder());
		
		return provider;
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswdEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/account/logout", "/account/change-password", "/account/change-username").fullyAuthenticated()
				.antMatchers("/account/login", "/account/register").anonymous()
				.antMatchers("/", "/account/is-username-taken").permitAll()
				.antMatchers("/h2-console/**", "/admin/**").hasAuthority("ADMIN")
			.and()
			.csrf().ignoringAntMatchers("/h2-console/**")
			.and()
			.formLogin().loginPage("/account/login")
			.and()
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/account/logout"))
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID") 
			.and().headers().frameOptions().disable();
	}
}
