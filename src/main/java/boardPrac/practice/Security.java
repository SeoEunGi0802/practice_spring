package boardPrac.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                    .antMatchers("/write").authenticated()
                    .antMatchers("/edit").authenticated()
                    .antMatchers("/delete").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                .and()
                    .exceptionHandling()
                        .accessDeniedPage("/login")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
