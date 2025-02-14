package me.geeksploit.enot.configuration;

import me.geeksploit.enot.SpringDataJpaUserDetailsService;
import me.geeksploit.enot.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] ANT_PATTERNS_API = {"/api", "/api/**/*"}; // TODO: make sure to tighten API access in production
    private static final String[] ANT_PATTERNS_FILE = {"/bower_components/**", "/bundle.js*", "/*.jsx", "/main.css"};
    private static final String[] ANT_PATTERNS_PATH = {"/"};

    @Autowired
    private SpringDataJpaUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(Manager.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(ANT_PATTERNS_API).permitAll()
                .antMatchers(ANT_PATTERNS_FILE).permitAll()
                .antMatchers(ANT_PATTERNS_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/");
    }

}