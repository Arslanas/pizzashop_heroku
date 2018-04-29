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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder amb) throws Exception {
        amb.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.formLogin().loginPage("/login").loginProcessingUrl("/login").successForwardUrl("/products").failureUrl("/login?error=true");
        security.logout().logoutUrl("/logout").logoutSuccessUrl("/products").invalidateHttpSession(true);
        security.formLogin().usernameParameter("username");
        security.formLogin().passwordParameter("password");
        security.formLogin().successForwardUrl("/products");
        security.rememberMe().rememberMeParameter("remember-me").key("pizzaShopKey");


        security.authorizeRequests().regexMatchers("/admin/.*").hasAuthority("ADMIN");
        security.authorizeRequests().regexMatchers("/user/registration").permitAll();
        security.authorizeRequests().regexMatchers("/user/.*").authenticated();
        security.authorizeRequests().regexMatchers("/.*").permitAll();
    }

}
