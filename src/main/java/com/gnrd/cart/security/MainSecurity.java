package com.gnrd.cart.security;

import com.gnrd.cart.security.jwt.JwtEntryPoint;/*Importa una clase personalizada JwtEntryPoint 
que se utiliza para manejar errores de autenticación. */
import com.gnrd.cart.security.jwt.JwtTokenFilter;/*Importa una clase personalizada JwtTokenFilter 
que se encarga de filtrar las peticiones y validar los tokens JWT. */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;/*Habilita la seguridad 
de Spring Security en la aplicación. */
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration/*Marca esta clase como una clase de configuración de Spring. */
@EnableWebSecurity/*Habilita la seguridad de Spring Security en la aplicación. */


@EnableGlobalMethodSecurity(prePostEnabled = true)
/* permite la seguridad a nivel de método, 
lo que significa que puedes asegurar 
métodos específicos con anotaciones de 
seguridad.
*/


public class MainSecurity {
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    /*
     * Aquí se inyecta una instancia de JwtEntryPoint, 
     * que es un componente personalizado utilizado para 
     * manejar errores de autenticación.
    */
    

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOriginPatterns(List.of("https://frontathomlab-production.up.railway.app/"
            , "http://localhost:3000"));
            configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT"
            , "DELETE", "PATCH", "OPTIONS"));
            configuration.setAllowCredentials(true);
            configuration.addExposedHeader("Message");
            configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
            return configuration;
        }).and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**","/auth/**", "/product/**", 
                "/product/subcategory/**", "/product/all/**", "/product/related/**", 
                "/category/**", "/subcategory/**")
                .permitAll()/*.anyRequest().authenticated()*/
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
