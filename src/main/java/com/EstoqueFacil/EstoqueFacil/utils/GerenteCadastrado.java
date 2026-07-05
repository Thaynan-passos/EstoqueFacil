package com.EstoqueFacil.EstoqueFacil.utils;

import com.EstoqueFacil.EstoqueFacil.model.Cargo;
import com.EstoqueFacil.EstoqueFacil.model.Endereco;
import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.model.Telefone;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GerenteCadastrado {

    @Value("${gerente.inicial.nome}")
    private String nome;

    @Value("${gerente.inicial.cpf}")
    private String cpf;

    @Value("${gerente.inicial.email}")
    private String email;

    @Value("${gerente.inicial.senha}")
    private String senhaPura;

    @Value("${gerente.inicial.endereco.rua}")
    private String rua;

    @Value("${gerente.inicial.endereco.numero}")
    private String numeroCasa;

    @Value("${gerente.inicial.endereco.cidade}")
    private String cidade;

    @Value("${gerente.inicial.endereco.estado}")
    private String estado;

    @Value("${gerente.inicial.endereco.cep}")
    private String cep;

    @Value("${gerente.inicial.endereco.bairro}")
    private String bairro;

    @Value("${gerente.inicial.telefone.numero}")
    private String telefoneNumero;

    @Value("${gerente.inicial.telefone.tipo}")
    private String tipoTelefone;

    @Bean
    CommandLineRunner init(FuncionarioService funcionarioService,
                           PasswordEncoder passwordEncoder) {

        return args -> {

            boolean existeGerente = funcionarioService.buscarTodosFuncionarios()
                    .stream()
                    .anyMatch(f -> f.getCargo() == Cargo.GERENTE);

            if (!existeGerente) {

                Funcionario gerente = new Funcionario();
                gerente.setNome(nome);
                gerente.setCpf(cpf);
                gerente.setEmail(email);
                gerente.setCargo(Cargo.GERENTE);


                Endereco endereco = new Endereco();
                endereco.setRua(rua);
                endereco.setNumeroCasa(numeroCasa);
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                endereco.setCep(cep);
                endereco.setBairro(bairro);

                gerente.setEndereco(endereco);

                Telefone telefone = new Telefone();
                telefone.setTelefone(telefoneNumero);
                telefone.setTipoTelefone(tipoTelefone);

                List<Telefone> telefones = new ArrayList<>();
                telefones.add(telefone);

                gerente.setTelefone(telefones);

                funcionarioService.cadastrarFuncionario(gerente, senhaPura);
            }
        };
    }
}