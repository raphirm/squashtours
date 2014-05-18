package squash.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import squash.service.UserDetailServiceImpl;

@Configuration
@EnableWebMvcSecurity


public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	AccessDeniedHandlerImpl deniedhandler = new AccessDeniedHandlerImpl();
    	deniedhandler.setErrorPage("/accessdenied.html");
        http
            .authorizeRequests()
                .antMatchers("/register.html**", "/", "/home", "/image/**", "/css/**", "/js/**", "/j_spring_security**", "index.html**", "about.html").permitAll()
                .antMatchers("/user/**").access("principal")
                .anyRequest().authenticated();
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
    @Bean
    public UserDetailServiceImpl userDetailsService(){
        return new UserDetailServiceImpl();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
    


}