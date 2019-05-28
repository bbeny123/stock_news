package org.beny.stock.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/rest/**")
                .authorizeRequests()
                .antMatchers("/rest/register/**").permitAll()
                .antMatchers(GET, "/rest/news/**").permitAll()
                .antMatchers("/rest/news/**").hasAuthority("ADMIN")
                .anyRequest().authenticated();
    }

}
