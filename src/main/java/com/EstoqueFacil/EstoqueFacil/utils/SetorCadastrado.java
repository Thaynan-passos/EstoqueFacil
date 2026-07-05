package com.EstoqueFacil.EstoqueFacil.utils;

import com.EstoqueFacil.EstoqueFacil.model.Endereco;
import com.EstoqueFacil.EstoqueFacil.model.Setor;
import com.EstoqueFacil.EstoqueFacil.model.TipoSetor;
import com.EstoqueFacil.EstoqueFacil.repository.SetorRepository; // Ou SetorService
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SetorCadastrado {


    // PROPRIEDADES DA GERÊNCIA

    @Value("${setor.gerencia.nome}") private String gerenciaNome;
    @Value("${setor.gerencia.capacidade}") private int gerenciaCapacidade;
    @Value("${setor.gerencia.tipo}") private TipoSetor gerenciaTipo;
    @Value("${setor.gerencia.endereco.rua}") private String gerenciaRua;
    @Value("${setor.gerencia.endereco.numero}") private String gerenciaNumero;
    @Value("${setor.gerencia.endereco.cidade}") private String gerenciaCidade;
    @Value("${setor.gerencia.endereco.estado}") private String gerenciaEstado;
    @Value("${setor.gerencia.endereco.cep}") private String gerenciaCep;
    @Value("${setor.gerencia.endereco.bairro}") private String gerenciaBairro;


    // PROPRIEDADES DO ALMOXARIFADO

    @Value("${setor.almoxarifado.nome}") private String almoxNome;
    @Value("${setor.almoxarifado.capacidade}") private int almoxCapacidade;
    @Value("${setor.almoxarifado.tipo}") private TipoSetor almoxTipo;
    @Value("${setor.almoxarifado.endereco.rua}") private String almoxRua;
    @Value("${setor.almoxarifado.endereco.numero}") private String almoxNumero;
    @Value("${setor.almoxarifado.endereco.cidade}") private String almoxCidade;
    @Value("${setor.almoxarifado.endereco.estado}") private String almoxEstado;
    @Value("${setor.almoxarifado.endereco.cep}") private String almoxCep;
    @Value("${setor.almoxarifado.endereco.bairro}") private String almoxBairro;


    // PROPRIEDADES DO FINANCEIRO

    @Value("${setor.financeiro.nome}") private String finNome;
    @Value("${setor.financeiro.capacidade}") private int finCapacidade;
    @Value("${setor.financeiro.tipo}") private TipoSetor finTipo;
    @Value("${setor.financeiro.endereco.rua}") private String finRua;
    @Value("${setor.financeiro.endereco.numero}") private String finNumero;
    @Value("${setor.financeiro.endereco.cidade}") private String finCidade;
    @Value("${setor.financeiro.endereco.estado}") private String finEstado;
    @Value("${setor.financeiro.endereco.cep}") private String finCep;
    @Value("${setor.financeiro.endereco.bairro}") private String finBairro;

    @Bean
    CommandLineRunner initSetores(SetorRepository setorRepository) {
        return args -> {

            // Inicializa a Gerência
            criarSetorSeNaoExistir(setorRepository, gerenciaNome, gerenciaCapacidade, gerenciaTipo,
                    gerenciaRua, gerenciaNumero, gerenciaCidade, gerenciaEstado, gerenciaCep, gerenciaBairro);

            // Inicializa o Almoxarifado
            criarSetorSeNaoExistir(setorRepository, almoxNome, almoxCapacidade, almoxTipo,
                    almoxRua, almoxNumero, almoxCidade, almoxEstado, almoxCep, almoxBairro);

            // Inicializa o Financeiro
            criarSetorSeNaoExistir(setorRepository, finNome, finCapacidade, finTipo,
                    finRua, finNumero, finCidade, finEstado, finCep, finBairro);
        };
    }


    private void criarSetorSeNaoExistir(SetorRepository repository, String nome, int capacidade, TipoSetor tipoStr,
                                        String rua, String numero, String cidade, String estado, String cep, String bairro) {


        // Verifica se já existe um setor cadastrado com esse Tipo específico

        boolean existe = repository.existsByTipoSetor(tipoStr);

        if (!existe) {
            Setor setor = new Setor();
            setor.setNomeSetor(nome);
            setor.setCapacidade(capacidade);
            setor.setTipoSetor(tipoStr);

            Endereco endereco = new Endereco();
            endereco.setRua(rua);
            endereco.setNumeroCasa(numero);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            endereco.setCep(cep);
            endereco.setBairro(bairro);

            setor.setEndereco(endereco);

            repository.save(setor);
            System.out.println("Setor inicial criado com sucesso: " + nome);
        }
    }
}
