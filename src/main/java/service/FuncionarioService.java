package service;

import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import model.FuncionarioModel;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    public void nomeValidar(String nome) {

        if (!nome.matches("[\\p{L} ]+")) {

            throw new CampoPreenchimento("Atenção: Preencha o campo corretamente, sem números ou símbolos");
        }
    }

    public void senhaValidar(String senha) {

        senha = senha.trim();

        if (senha.length() < 8 || senha.length() > 14) {

            throw new ErroDePreenchimentoInvalidoException("Atenção: Senha incompleta, ela deve ter no mínimo 8 dígitos e no máximo 14 dígitos");

        }

        String senhaForte= "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,14}$";

        if (!senha.matches(senhaForte)) {
            throw new ErroDePreenchimentoInvalidoException(
                    "A senha deve conter pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial."
            );
        }
    }

    public void validarFuncionario(FuncionarioModel funcionario){
        nomeValidar(funcionario.getNome());
        senhaValidar(funcionario.getSenhaHash());
    }



}
