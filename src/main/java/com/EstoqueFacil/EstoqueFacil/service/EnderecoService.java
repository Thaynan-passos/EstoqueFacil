package com.EstoqueFacil.EstoqueFacil.service;


import com.EstoqueFacil.EstoqueFacil.dao.EnderecoDAO;
import com.EstoqueFacil.EstoqueFacil.model.EnderecoModel;
import com.EstoqueFacil.EstoqueFacil.model.FornecedorModel;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnderecoService {


    private final EnderecoDAO enderecoDAO;

    public EnderecoService(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    public static void validarID(int id){
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
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

    public static void ruaValidar(String rua){

        if(rua.length() < 3){
            throw new ErroDePreenchimentoInvalidoException("\nO nome da rua é muito curto");
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

    public EnderecoModel  cadastrarEndereco(EnderecoModel  endereco) {

        validarEndereco(endereco);

        return enderecoDAO.save(endereco);
    }

    public EnderecoModel  buscarPorId(int id) {

        return enderecoDAO.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum endereço foi encontrado"));
    }

    public EnderecoModel buscarPorCep(String cep) {
        return enderecoDAO.findByCep(cep).orElseThrow(() -> new NoSuchElementException("Não foi possível achar nenhum endereço ligado a esse cep"));
    }

    public List<EnderecoModel> buscarTodosEnderecos() {

        return this.enderecoDAO.findAll();
    }

    public EnderecoModel  atualizarEnderecoPorCep(String cep, EnderecoModel dadosAtualizados) {

        EnderecoModel   enderecoAtualizado = buscarPorCep(cep);

        enderecoAtualizado.setBairro(dadosAtualizados.getBairro());
        enderecoAtualizado.setCidade(dadosAtualizados.getCidade());
        enderecoAtualizado.setEstado(dadosAtualizados.getEstado());
        enderecoAtualizado.setNumeroCasa(dadosAtualizados.getNumeroCasa());

        return enderecoDAO.save( enderecoAtualizado);
    }

    public void deletarPorId(int id) {

      if(!enderecoDAO.existsById(id)){
          throw new NoSuchElementException("Não foi encontrado nenhum endereço");
      }
      enderecoDAO.deleteById(id);
    }

    public void validarEndereco(EnderecoModel endereco) {

        cepValidar(endereco.getCep());
        bairroValidar(endereco.getBairro());
        cidadeValidar(endereco.getCidade());
        numeroCasaValidar(endereco.getNumeroCasa());

        if(enderecoDAO.existsByCep(endereco.getCep())) {
            throw new CampoPreenchimento("Esse Cep já foi cadastrado");
        }
    }


}
