package com.eazybytes.springsecsection1.config;

import com.eazybytes.springsecsection1.exceptionHandling.CustomAccessDeniedHandler;
import com.eazybytes.springsecsection1.exceptionHandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Profile("!prod")
@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionMangementConfig ->
                        sessionMangementConfig.invalidSessionUrl("/invalidSession").maximumSessions(3).maxSessionsPreventsLogin(true))
//                .sessionManagement(sessionMangementConfig ->
//                sessionMangementConfig.sessionFixation(sessionFixationConfigurer ->
//                                sessionFixationConfigurer.newSession())) //TO change the default session fixation strategey
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) //only HTTP
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myBalance","/myLoans","myCards").authenticated()
                .requestMatchers("/notices","/contact","/error","/register","invalidSession").permitAll());
        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
        http.httpBasic(httpbasicconfig -> httpbasicconfig.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(exceptionHandlerConfig -> exceptionHandlerConfig.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();

    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }


}
