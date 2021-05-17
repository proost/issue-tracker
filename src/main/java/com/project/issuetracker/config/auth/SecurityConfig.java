package com.project.issuetracker.config.auth;

import com.project.issuetracker.domain.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;

@Profile({"dev", "prod"})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    private final AuthenticationSuccessHandler rememberMeAuthenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final RememberMeServices rememberMeServices;

    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider,
                          @Qualifier("form") AuthenticationSuccessHandler loginAuthenticationSuccessHandler,
                          @Qualifier("remember-me") AuthenticationSuccessHandler rememberMeAuthenticationSuccessHandler,
                          AuthenticationFailureHandler authenticationFailureHandler,
                          RememberMeServices rememberMeServices) {
        this.authenticationProvider = authenticationProvider;
        this.loginAuthenticationSuccessHandler = loginAuthenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.rememberMeServices = rememberMeServices;
        this.rememberMeAuthenticationSuccessHandler = rememberMeAuthenticationSuccessHandler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .headers().frameOptions().disable()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginProcess")
                    .successHandler(loginAuthenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("SESSION")
                .and()
                    .authorizeRequests()
                    .antMatchers("/registration").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .rememberMe()
                    .rememberMeServices(rememberMeServices)
                    .authenticationSuccessHandler(rememberMeAuthenticationSuccessHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
