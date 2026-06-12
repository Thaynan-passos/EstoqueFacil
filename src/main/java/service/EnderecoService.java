package service;


import exceptions.ErroDePreenchimentoInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

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

}
