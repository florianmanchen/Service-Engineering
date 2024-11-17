package com.serviceengeneering.lvaservice.Auth;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.Map;
import java.util.Set;

import static javax.management.Query.and;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").authenticated()
                        .requestMatchers("/lvaLeaders1/**").hasRole("LVAAdmin")
                        .requestMatchers("/students1/**").hasRole("LVALeiter")
                        .requestMatchers("/lvas1/**").hasRole("LVALeiter")
                        .requestMatchers("/exerciseSubmission1/**").hasRole("Studierender")
                        .requestMatchers("/exerciseCorrection1/**").hasRole("LVALeiter")
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2LoginCustomizer());

        return http.build();
    }

    private Customizer<OAuth2LoginConfigurer<HttpSecurity>> oauth2LoginCustomizer() {
        return oauth2Login -> oauth2Login
                .loginPage("/login")  // Custom login page
                .userInfoEndpoint(userInfo -> userInfo
                        .oidcUserService(this.oidcUserService()) // Custom OIDC user service
                        .userService(this.oauth2UserService())   // Custom OAuth2 user service
                )
                .defaultSuccessUrl("/", true) // Redirect to /home after login
                .failureUrl("/login?error");      // Redirect to custom error page on failure
    }

    private OidcUserService oidcUserService() {
        OidcUserService oidcUserService = new OidcUserService();
        return oidcUserService;
    }

    private DefaultOAuth2UserService oauth2UserService() {
        return new DefaultOAuth2UserService() {
            @Override
            public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
                OAuth2User user = super.loadUser(userRequest);
                Map<String, Object> attributes = user.getAttributes();
                // Custom logic on user attributes
                return user;
            }
        };
    }
}

