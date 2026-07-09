package com.EstoqueFacil.EstoqueFacil.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.repository.FuncionarioRepository;

@Service
public class AuthService implements UserDetailsService {

    private final FuncionarioRepository repository;

    public AuthService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) {

        Funcionario f = repository.findByCpf(cpf.replaceAll("\\D", ""))
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado")
                );


        if (!f.isAtivo()) {
            throw new UsernameNotFoundException("Funcionário desativado.");
        }


        return new org.springframework.security.core.userdetails.User(
                f.getCpf(),
                f.getSenhaHash(),
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + f.getCargo().name()
                        )
                )
        );
    }
}