package com.dbs.bank.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
      authenticationManagerBuilder
              .userDetailsService(userDetailsService)
              .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
	
	@Autowired
  private UserDetailsService userDetailsService;
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/api/v1/**")
			.permitAll().
			antMatchers("/api/v1/customer/**")
				.hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/v1/banker/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();

		http.csrf().disable();
	}

	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        http.httpBasic().and().authorizeRequests()
//        .antMatchers("/api/v1/customer/**").hasRole("USER")
//        .antMatchers("/api/v1/banker/**").hasRole("ADMIN")
//        .and()
//        .csrf().disable();
//    }
//  
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception
//    {
//        auth.inMemoryAuthentication()
//        .withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
//        .and()
//        .withUser("sush").password(passwordEncoder().encode("sush123")).roles("USER", "ADMIN");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
