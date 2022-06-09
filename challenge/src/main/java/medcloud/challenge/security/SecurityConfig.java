package medcloud.challenge.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws  Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).hasAnyRole("ADMIN", "USER")
                .and()
                .httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(System.getenv("USER_PASS"));
        UserDetails user = User.builder().username("user").password("{noop}" + System.getenv("USER_PASS")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password("{noop}" + System.getenv("ADMIN_PASS")).roles("ADMIN").build();
        auth.inMemoryAuthentication().withUser(user).withUser(admin);
    }
}