package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private CustomLoginSuccessHandler successHandler ;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				// URLs matching for access rights
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/createform/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/edit/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/update/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/delete/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/home/**").hasAnyAuthority("ROLE_USER")
				.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/new/**").hasAnyAuthority("ROLE_NEW")
				.antMatchers("/superuser/**").hasAnyAuthority("ROLE_SUPERUSER")
				.anyRequest().authenticated()
				.and()
				// form login
				.csrf().disable().formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.successHandler(successHandler)
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
				// logout
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSizePerFile(20*1024*1024);
		
		return commonsMultipartResolver;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/views/**","/fonts/**");
	}
	
	
	

}
