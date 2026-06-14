package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.FuncionarioRepository;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.FuncionarioModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;


    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

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


    public FuncionarioModel cadastrarFuncionario(FuncionarioModel funcionario) {

        validarFuncionario(funcionario);

        return funcionarioRepository.save(funcionario);
    }

    public FuncionarioModel buscarPorCpf(String cpf) {

        return funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new NoSuchElementException("Nenhum funcionário encontrado"));
    }

    public FuncionarioModel buscarPorEmail(String email) {

        return funcionarioRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Nenhum funcionário foi encontrado"));
    }

    public List<FuncionarioModel> buscarTodosFuncionarios() {

        return this.funcionarioRepository.findAll();
    }

    public FuncionarioModel atualizarFuncionarioPorCpf(String cpf,FuncionarioModel dadosAtualizados) {

        FuncionarioModel  funcionarioAtualizado = buscarPorCpf(cpf);

        if(!funcionarioAtualizado.getEmail().equals(dadosAtualizados.getEmail())
        && funcionarioRepository.existsByEmail(dadosAtualizados.getEmail())) {
            throw new ErroDePreenchimentoInvalidoException("Email já está em uso");
        }

        funcionarioAtualizado.setNome(dadosAtualizados.getNome());
        funcionarioAtualizado.setEmail(dadosAtualizados.getEmail());
        funcionarioAtualizado.setSenhaHash(dadosAtualizados.getSenhaHash());

        return funcionarioRepository.save(funcionarioAtualizado);
    }

    public FuncionarioModel deletarPorCpf(String cpf) {

        if (!funcionarioRepository.existsByCpf(cpf)) {
           throw new NoSuchElementException("Nenhum funcionário encontrado");
        }
        return funcionarioRepository.deleteByCpf(cpf);

    }

    public void validarFuncionario(FuncionarioModel funcionario){

        nomeValidar(funcionario.getNome());
        senhaValidar(funcionario.getSenhaHash());


        if(funcionarioRepository.existsByCpf(funcionario.getCpf())){
            throw new CampoPreenchimento("CPF já cadastrado");
        }

        if(funcionarioRepository.existsByEmail(funcionario.getEmail())){
            throw new CampoPreenchimento("Este Email já está cadastrado");
        }
    }
}
