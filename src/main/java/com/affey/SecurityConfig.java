package com.affey;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.affey.security.AetherWebSecurityExpressionHandler;
import com.affey.security.CustomAuthEntryPoint;


/**
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  

	  @Autowired
	  private CustomAuthEntryPoint customEntryPoint;
	  
	  @Autowired
	  private AetherWebSecurityExpressionHandler expressionHandler;
	  
	  @Autowired
	  private PasswordEncoder encoder;
	  
	@Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource)
	    throws Exception {
	    auth
	      .jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(getUsersByUsernameQuery())
	      .authoritiesByUsernameQuery(getAuthoritiesByUsernameQuery())
	      .passwordEncoder(encoder);
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .authorizeRequests()
	       .antMatchers("/v1/api/admin/**").hasAuthority("ADMIN_API_CALL")
	       .antMatchers("/v1/api/theatre/**").hasAuthority("CUSTOMER_API_CALL")
	      .antMatchers("/v1/callback/**").permitAll()
	      .antMatchers("/v2/api-docs/**").permitAll()
	      .antMatchers("/webjars/**").permitAll()
	      .antMatchers("/templates/**").permitAll()
	      .antMatchers("/js/**").permitAll()
	      .antMatchers("/css/**").permitAll()
	      .antMatchers("/ui-config.json").permitAll()
	      .antMatchers("/").permitAll()
	      .anyRequest().authenticated()
	      .and()
	      .httpBasic().authenticationEntryPoint(customEntryPoint)
	      .and()
	      .csrf().disable();
	  }

	  private String getUsersByUsernameQuery() {
	    return "SELECT user_name, password, enabled FROM user WHERE user_name=? AND enabled=true";
	  }

	  private String getAuthoritiesByUsernameQuery() {
	    return "SELECT ur.user_name, rp.permission_name" +
	        " FROM role r, user_role ur, role_permission rp" +
	        " WHERE ur.user_name=?" +
	        " AND r.role_name = ur.role_name" +
	        " AND r.role_name = rp.role_name";
	  }
}
