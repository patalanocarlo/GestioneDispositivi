package CarloPatalano.GestioneDispositivi.Configuration;


import CarloPatalano.GestioneDispositivi.Entities.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(http -> http.disable());
        httpSecurity.csrf(http -> http.disable());
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(http -> {
            http.requestMatchers("/auth/**").permitAll();
            http.requestMatchers(HttpMethod.GET, "/dipendenti/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name());
            http.requestMatchers(HttpMethod.POST, "/dipendenti/**").hasAuthority(Role.ADMIN.name());
            http.requestMatchers(HttpMethod.PUT, "/dipendenti/**").hasAuthority(Role.ADMIN.name());
            http.requestMatchers(HttpMethod.DELETE, "/dipendenti/**").hasAuthority(Role.ADMIN.name());
            http.requestMatchers(HttpMethod.POST, "/dipendenti").permitAll();
            http.anyRequest().authenticated();
        });
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
