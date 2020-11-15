package com.example.security.inmemory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // The authentication requirements / criteria

        auth.inMemoryAuthentication()
                .withUser("piyush")
                .password("piyush1234")
                .authorities("adm")
                .and()
                .withUser("karan")
                .password("karan1234")
                .authorities("usr");



    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.
                httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").hasAnyAuthority("usr", "adm")
                .antMatchers("/admin/**").hasAuthority("adm")
                .antMatchers("/**").permitAll()

                .and().formLogin();
    }


    @Bean
    public PasswordEncoder getPassEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
