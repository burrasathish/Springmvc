package com.mountblue.Blogproject.AppSecurityConfig;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; //1
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userdetails;

    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userdetails);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http
                 .headers().frameOptions().sameOrigin()
                 .and().authorizeRequests()
                 .antMatchers("/resources/**","/css/**").permitAll()
                 .antMatchers("/","/SignUp","/userAcc/**","/home","/postCon/","/Global","/sorting","/showPostData/**").permitAll()
                 .antMatchers("/home","/showAdmin/**").hasAnyRole("ADMIN","USER")
                 .anyRequest().authenticated()
                 .and()
                 .formLogin()
                 .loginPage("/loginForm")
                 .defaultSuccessUrl("/homePage")
                 .failureUrl("/login?error")
                 .permitAll().and()
                 .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginForm?logout")
                .deleteCookies("my-remember-me-cookie")
                .permitAll()
                .and().
        httpBasic();

                }



}

