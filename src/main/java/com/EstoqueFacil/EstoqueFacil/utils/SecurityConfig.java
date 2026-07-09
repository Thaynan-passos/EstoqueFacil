package com.EstoqueFacil.EstoqueFacil.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.EstoqueFacil.EstoqueFacil.service.AuthService;

@Configuration
public class SecurityConfig {

    private final AuthService authService;

    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(authService)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/setup", "/recuperar-senha", "/css/**", "/js/**", "/images/**", "/static/**", "/favicon.ico").permitAll()
                        // GERENTE
                        .requestMatchers("/dashboard-gerente/**").hasRole("GERENTE")
                        .requestMatchers("/relatorio-financeiro/**").hasRole("GERENTE")
                        .requestMatchers("/cadastro/**").hasRole("GERENTE")
                        .requestMatchers("/analise-gerente/**").hasRole("GERENTE")
                        .requestMatchers("/requisicao/aprovar/**").hasRole("GERENTE")
                        .requestMatchers("/requisicao/rejeitar/**").hasRole("GERENTE")

                        // ALMOXARIFE
                        .requestMatchers("/dashboard-almoxarife/**").hasRole("ALMOXARIFADO")
                        .requestMatchers("/controle-estoque/**").hasRole("ALMOXARIFADO")
                        .requestMatchers("/cadastrar-produto/**").hasRole("ALMOXARIFADO")
                        .requestMatchers("/entrada-materiais/**").hasRole("ALMOXARIFADO")
                        .requestMatchers("/inventario/**").hasRole("ALMOXARIFADO")
                        .requestMatchers("/saidas-materiais/**").hasRole("ALMOXARIFADO")

                        // FUNCIONÁRIO
                        .requestMatchers("/dashboard-funcionario/**").hasRole("FINANCEIRO")
                        .requestMatchers("/requisicao/**").hasRole("FINANCEIRO")
                        .requestMatchers("/historico-requisicoes/**").hasRole("FINANCEIRO")
                        .requestMatchers("/requisicao/historico/**").hasRole("FINANCEIRO")
                        .requestMatchers("/minhas-solicitacoes/**").hasRole("FINANCEIRO")


                        // PÚBLICAS
                        .requestMatchers("/", "/login", "/setup", "/css/**", "/js/**", "/images/**", "/static/**", "/favicon.ico").permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("cpf")
                        .passwordParameter("senha")
                        .successHandler((request, response, authentication) -> {

                            var authorities = authentication.getAuthorities();

                            boolean isGerente = authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_GERENTE"));

                            boolean isAlmoxarife = authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ALMOXARIFADO"));

                            if (isGerente) {
                                response.sendRedirect("/dashboard-gerente");
                            } else if (isAlmoxarife) {
                                response.sendRedirect("/dashboard-almoxarife");
                            } else {
                                response.sendRedirect("/dashboard-funcionario");
                            }
                        })
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}