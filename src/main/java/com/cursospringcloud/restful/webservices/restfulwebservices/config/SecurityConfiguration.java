package com.cursospringcloud.restful.webservices.restfulwebservices.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe criada com o propósito de sobreescrever a auto-configuration do Spring Security
 * afim de desabilitar o problema com o header "X-Frame-Options" que impede de logar dentro
 * da página /h2-console. Com essa implementação ativa (descomentar @Configuration) o
 * Spring Security é DESABILITADO.
 */
//@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

}