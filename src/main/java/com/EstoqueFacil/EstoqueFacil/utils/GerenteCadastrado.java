package com.EstoqueFacil.EstoqueFacil.utils;

import com.EstoqueFacil.EstoqueFacil.model.Cargo;
import com.EstoqueFacil.EstoqueFacil.model.Endereco;
import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.model.Telefone;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GerenteCadastrado {

    @Bean
    CommandLineRunner init(FuncionarioService funcionarioService,
                           PasswordEncoder passwordEncoder) {

        return args -> {

            boolean existeGerente = funcionarioService.buscarTodosFuncionarios()
                    .stream()
                    .anyMatch(f -> f.getCargo() == Cargo.GERENTE);

            if (!existeGerente) {

                Funcionario gerente = new Funcionario();
                gerente.setNome("Administrador");
                gerente.setCpf("34787844091");
                gerente.setEmail("gerente@sistema.com");
                gerente.setCargo(Cargo.GERENTE);
                gerente.setNivelAcesso(10);

               String senhaPura = "Gerente@123";

                Endereco endereco = new Endereco();
                endereco.setRua("Rua Exemplo");
                endereco.setNumeroCasa("123");
                endereco.setCidade("Olinda");
                endereco.setEstado("PE");
                endereco.setCep("53010000");
                endereco.setBairro("Peixinhos");

                gerente.setEndereco(endereco);

                Telefone telefone = new Telefone();
                telefone.setTelefone("81999999999");
                telefone.setTipoTelefone("CELULAR");

                List<Telefone> telefones = new ArrayList<>();
                telefones.add(telefone);

                gerente.setTelefone(telefones);

                funcionarioService.cadastrarFuncionario(gerente, senhaPura);
            }
        };
    }
}