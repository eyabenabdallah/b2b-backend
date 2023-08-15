package tn.taxi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import tn.taxi.services.MyLogDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {
	
	@Autowired
	private MyLogDetailsService logDetailsService;
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	/*@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();

		}*/
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(logDetailsService).passwordEncoder(passwordEncoder);
	}
	

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors().and()
        .csrf()
        .disable()
        .authorizeHttpRequests() .requestMatchers("/authenticate").permitAll()
		                         
        //.requestMatchers("/log/retrieve-all-agents").hasAuthority("ROLE_ADMIN")
							 	.requestMatchers("/log/**").permitAll()
							 	.requestMatchers("/course/**").permitAll()
							 	.requestMatchers("/taxi/**").permitAll()
							 	.requestMatchers("/client/**").permitAll()
							 	.requestMatchers("/societe/**").permitAll()
							 	
							 	.anyRequest().authenticated()
                                 //.and().formLogin().loginPage("/login").permitAll()
                     			 .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                 .logoutSuccessUrl("/authenticate?logout")
                                 .deleteCookies("JSESSIONID")
                                 .invalidateHttpSession(true).permitAll()
                                 .and().httpBasic().
                                  and().
                                  exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
                                  and().
                  				// make sure we use stateless session; session won't be used to
                  				// store user's state.
                  				  sessionManagement()
                  				 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                                 
                                 
		// Add a filter to validate the tokens with every request
				http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
				return http.build();
	}

}
