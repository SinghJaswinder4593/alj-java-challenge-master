package jp.co.axa.apidemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class UserDetailsConfig extends WebSecurityConfigurerAdapter {



    //To Set the authentication as per the roles
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("User").password("{noop}password").roles("User")
                .and()
                .withUser("Admin").password("{noop}password").roles("Admin");
    }

    //To Set the access as per the SpringSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("http://localhost:8080/h2-console/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin();

        http.csrf().disable();
    }

    }


