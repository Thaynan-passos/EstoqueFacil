package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.TelefoneRepository;
import com.EstoqueFacil.EstoqueFacil.model.TelefoneModel;
import exceptions.CampoPreenchimento;
import exceptions.TelefoneInvalidoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;

    public TelefoneService(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }

    public static void numeroTelefoneValidar(String numerotelefone) {

        String numeroLimpo = numerotelefone.replaceAll("[^0-9]", "");


        if (!numeroLimpo.matches("^\\d{11}$")) {

          throw new TelefoneInvalidoException("Atenção: O telefone deve contar 11 digitos (DDD + número)!");
        }

        int ddd = Integer.parseInt(numeroLimpo.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
           throw new TelefoneInvalidoException("Atenção: DDD inválido. Deve estar entre 11 e 99.");
        }
    }

    public TelefoneModel cadastrarTelefone(TelefoneModel telefoneModel){

        validarTelefone(telefoneModel);

        return telefoneRepository.save(telefoneModel);
    }

    public TelefoneModel  buscarPorId(int id) {

        return telefoneRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum telefone foi encontrado"));
    }

    public List<TelefoneModel> buscarTodosTelefones() {

        return this.telefoneRepository.findAll();
    }

    public TelefoneModel atualizarTelefonePorId(int id,TelefoneModel dadosAtualizado) {

        TelefoneModel telefoneNovo = buscarPorId(id);

        if(!telefoneNovo.getTelefone().equals(dadosAtualizado.getTelefone()) && telefoneRepository.existsByTelefone(dadosAtualizado.getTelefone())) {
            throw new CampoPreenchimento("Já existe telefone!");
        }

        telefoneNovo.setTelefone(dadosAtualizado.getTelefone());
        telefoneNovo.setTipoTelefone(dadosAtualizado.getTipoTelefone());

        return telefoneRepository.save(telefoneNovo);
    }

    public void deletarTelefonePorId(int id) {

        if(!telefoneRepository.existsById(id)){
            throw new NoSuchElementException("Não foi encontrado nenhum telefone");
        }

        telefoneRepository.deleteById(id);
    }

    public void validarTelefone(TelefoneModel telefone) {

        numeroTelefoneValidar(telefone.getTelefone());
    }

}


