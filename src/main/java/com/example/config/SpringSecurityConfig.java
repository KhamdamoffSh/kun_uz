package com.example.config;

import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

   /* @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication (login,password)
        String password = UUID.randomUUID().toString();
        System.out.println("User Password mazgi: " + password);

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}" + password)
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$sqZSjdI58m/mf3CtTa4Ob.XxwDHtP55jfIAX5fiUma895Tn8fy9Ci")
                .roles("ADMIN")
                .build();

        UserDetails moderator = User.builder()
                .username("moderator")
                .password("{bcrypt}$2a$12$sqZSjdI58m/mf3CtTa4Ob.XxwDHtP55jfIAX5fiUma895Tn8fy9Ci")
                .roles("MODERATOR")
                .build();
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user, admin, moderator));
        return authenticationProvider;
    }*/


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    public static String[] AUTH_WHITELIST = {"/api/v1/auth/**",
            "/api/v1/news/**",
            "/api/v1/profile/**",
            "/api/v1/articleLike/**",
            "/api/v1/articleType/**",
            "api/v1/articleSaved**",
            "/api/v1/category/**",
            "/api/v1/attach/**",
            "/api/v1/comment/**",
            "/api/v1/commentLike/**",
            "/api/v1/email/**",
            "/api/v1/region/**"};

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication (login,password)
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    private PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MD5Util.encode(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((c) ->
                c.requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers("/api/v1/article/moderator/**").hasRole("MODERATOR")
                        .requestMatchers("/api/v1/articleType/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/category/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/attach/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/email/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/profile/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/region/admin", "/api/v1/region/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
