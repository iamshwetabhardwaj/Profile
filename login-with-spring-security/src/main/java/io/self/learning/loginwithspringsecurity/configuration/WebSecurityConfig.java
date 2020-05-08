package io.self.learning.loginwithspringsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created in login-with-spring-security.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Qualifier("userDetailsServiceImpl")
        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
                auth.userDetailsService(userDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/templates/**",
                        "/css/**", "/js/**",
                        "/")
                    .permitAll()
                    .antMatchers("/error").permitAll()
                    .antMatchers("/home").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/forgot-password").permitAll()
                    .antMatchers("/reset-password").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    //.loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/home", true)
                    //.successForwardUrl("/home")
                    .failureUrl("/login?error=true")
                    .permitAll()
                    //.failureHandler(authenticationFailureHandler())
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .permitAll()
                //.logoutSuccessHandler(logoutSuccessHandler())
                ;
        }

        @Bean
        public AuthenticationManager customAuthenticationManager() throws Exception {
                return authenticationManager();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        }

} 