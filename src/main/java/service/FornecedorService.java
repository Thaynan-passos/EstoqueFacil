package service;

import exceptions.CampoPreenchimento;
import model.FornecedorModel;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {


    public void nomeValidar(String nome){

        if(nome.length() < 3 || nome.length() > 100){
            throw new CampoPreenchimento("\nO nome deve ter entre 3 e 100 caracteres");
        }

        if(!nome.matches("[\\p{L} .'-]+")){
            throw new CampoPreenchimento("\ntenção: O nome contém caracteres inválido");
        }
    }


    public void validarFornecedor(FornecedorModel fornecedor){
        nomeValidar(fornecedor.getRazaoSocial());
    }
}
