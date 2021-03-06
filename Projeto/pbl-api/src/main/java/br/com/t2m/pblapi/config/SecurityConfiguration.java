package br.com.t2m.pblapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.t2m.pblapi.config.jwt.AuthEntryPointJwt;
import br.com.t2m.pblapi.config.jwt.AuthTokenFilter;
import br.com.t2m.pblapi.config.services.PblUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	PblUserDetailsService userDetailsService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	 private static final String[] AUTH_WHITELIST = {

	            // -- swagger ui
	            "/swagger-resources/**",
	            "/swagger-ui.html",
	            "/v2/api-docs",
	            "/webjars/**",
	            
	            // -- auth ui
	            "/pbl-api/auth/**",
	            
//	            // -- desenvolvimento
//	            "/aluno/**",
//	            "/professor/**",
//	            "/empresa/**",
//	            "/tipoContato/**",
//	    		"/temaPbl/**",
//	    		"/pbl/**",
//	    		"/disciplina/**",
	    		"/atividades/**",
	    		"/atividades/pbl/**",
//	    		"/problema/**",
	 };

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
		.anyRequest().authenticated();
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
}
