package squash.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import squash.service.UserDetailServiceImpl;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration {
    @Bean
    public UserDetailServiceImpl userDetailsService(){
        return new UserDetailServiceImpl();
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception  {
       auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
    @Configuration
    @Order(1)
    public static class ApiWebSeucrityConfigurationAdapter extends WebSecurityConfigurerAdapter{
    	protected void configure(HttpSecurity http) throws Exception{
    		http
    			.antMatcher("/api/**")
    			.authorizeRequests()
    				.antMatchers("/api/register").permitAll()
    				.antMatchers("/user/**").hasRole("admin")
    				.anyRequest().authenticated()
    				.and()
    				.httpBasic()
    				.and()
    				.csrf().disable();
    	}
    }
    @Configuration
    public static class WebSeucrityConfigurationAdapter extends WebSecurityConfigurerAdapter{
    	protected void configure(HttpSecurity http) throws Exception {
        	AccessDeniedHandlerImpl deniedhandler = new AccessDeniedHandlerImpl();
        	deniedhandler.setErrorPage("/accessdenied.html");
            http
                .authorizeRequests()
                    .antMatchers("/register.html**", "/", "/home", "/image/**", "/css/**", "/js/**", "/j_spring_security**", "index.html**", "about.html").permitAll()
                    .antMatchers("/user/**").access("principal")
                    .anyRequest().authenticated().and();
                    
     
            http
                .formLogin()
                	.loginProcessingUrl("/j_spring_security_check")
                    .loginPage("/index.html?login")
                    .failureUrl("/index.html?authfail")
                    .permitAll()
                    .and()
                .logout()
                	.logoutUrl("/j_spring_security_logout")
                	.logoutSuccessUrl("/index.html?logout")
                    .permitAll()
                    .and()
                .exceptionHandling()
                	.accessDeniedHandler(deniedhandler);
        }
    }
    
    

    


}