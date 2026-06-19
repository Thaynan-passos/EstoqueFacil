package com.EstoqueFacil.EstoqueFacil.service;


import com.EstoqueFacil.EstoqueFacil.repository.EnderecoRepository;
import com.EstoqueFacil.EstoqueFacil.model.Endereco;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnderecoService {


    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository =  enderecoRepository;
    }


    public static void estadoValidar(String estado) {

        //apenas letras maiúsculas e exatas duas letras
        if (!estado.matches("[A-Z]{2}")) {
            throw new ErroDePreenchimentoInvalidoException("\nO Estado deve ser a sigla (ex: PE, SP) em maiúsculo");
        }
    }

    public static void cepValidar(String cep){



        //exatos 8 dígitos
        if(!cep.trim().matches("^\\d{5}-\\d{3}$|^\\d{8}$")){
            throw new ErroDePreenchimentoInvalidoException("\nO CEP deve conter exatamente 8 números");
        }


    }

    public static void bairroValidar(String bairro){


        if(bairro.length() < 2){
            throw new ErroDePreenchimentoInvalidoException("\nO nome do bairro é muito curto");
        }
    }

    public static void cidadeValidar(String cidade){

        if(cidade.length() < 3){
            throw new ErroDePreenchimentoInvalidoException("\nO nome da cidade é muito curto");
        }
    }

    public static void numeroCasaValidar(String numeroCasa){

        if(!numeroCasa.trim().matches("^[0-9]{1,5}([A-Za-z]|\\\\s[A-Za-z]|-A)?$")){
            throw new ErroDePreenchimentoInvalidoException("\nO número da casa é inválido");
        }
    }

    public Endereco cadastrarEndereco(Endereco endereco) {

        validarEndereco(endereco);

        return enderecoRepository.save(endereco);
    }

    public Endereco buscarPorId(int id) {

        return enderecoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum endereço foi encontrado"));
    }

    public Endereco buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep).orElseThrow(() -> new NoSuchElementException("Não foi possível achar nenhum endereço ligado a esse cep"));
    }

    public List<Endereco> buscarTodosEnderecos() {

        return this.enderecoRepository.findAll();
    }

    public Endereco atualizarEnderecoPorCep(String cep, Endereco dadosAtualizados) {

        Endereco enderecoAtualizado = buscarPorCep(cep);

        enderecoAtualizado.setBairro(dadosAtualizados.getBairro());
        enderecoAtualizado.setCidade(dadosAtualizados.getCidade());
        enderecoAtualizado.setEstado(dadosAtualizados.getEstado());
        enderecoAtualizado.setNumeroCasa(dadosAtualizados.getNumeroCasa());

        return enderecoRepository.save( enderecoAtualizado);
    }

    public Endereco atualizarEnderecoPorId(int id, Endereco dadosAtualizados) {

        Endereco enderecoAtualizado = buscarPorId(id);

        enderecoAtualizado.setBairro(dadosAtualizados.getBairro());
        enderecoAtualizado.setCidade(dadosAtualizados.getCidade());
        enderecoAtualizado.setEstado(dadosAtualizados.getEstado());
        enderecoAtualizado.setNumeroCasa(dadosAtualizados.getNumeroCasa());


        return enderecoRepository.save( enderecoAtualizado);

    }

    public Endereco deletarPorId(int id) {

      if(!enderecoRepository.existsById(id)){
          throw new NoSuchElementException("Não foi encontrado nenhum endereço");
      }
     return  enderecoRepository.deleteById(id);
    }

    public void validarEndereco(Endereco endereco) {

        cepValidar(endereco.getCep());
        bairroValidar(endereco.getBairro());
        cidadeValidar(endereco.getCidade());
        numeroCasaValidar(endereco.getNumeroCasa());
        estadoValidar(endereco.getEstado());

        if(enderecoRepository.existsByCep(endereco.getCep())) {
            throw new CampoPreenchimento("Esse Cep já foi cadastrado");
        }
    }


}
