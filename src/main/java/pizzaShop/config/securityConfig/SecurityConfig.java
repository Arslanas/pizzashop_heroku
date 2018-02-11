package pizzaShop.config.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@ComponentScan("pizzaShop")
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder amb) throws Exception{
        amb.inMemoryAuthentication()
                .withUser("Arslan").password("pass").roles("USER");
//        amb.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity security)throws Exception{
//        security.authorizeRequests().regexMatchers("/products/addProduct").hasAuthority("ROLE_USER");
//        security.formLogin();
//        security.formLogin().successForwardUrl("/products");
    }
}
