package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.SetorRepository;

import com.EstoqueFacil.EstoqueFacil.model.Setor;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository){
        this.setorRepository = setorRepository;
    }

    public Setor cadastrarSetor(Setor setorModel){

        return setorRepository.save(setorModel);
    }

    public Setor buscarPorId(int id) {

        return setorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum telefone foi encontrado"));
    }


    public List<Setor> buscarTodosSetores() {

        return this.setorRepository.findAll();
    }

    public Setor atualizarSetorPorId(int id, Setor dadosAtualizado) {

       Setor setorNovo = buscarPorId(id);



       setorNovo.setCapacidade(dadosAtualizado.getCapacidade());
       setorNovo.setOrcamentoMensal(dadosAtualizado.getOrcamentoMensal());
       setorNovo.setNomeSetor(dadosAtualizado.getNomeSetor());

       return setorRepository.save(setorNovo);
    }

    public Setor deletarSetorPorId(int id){

        if(!setorRepository.existsById(id)){
            throw new NoSuchElementException("Não foi encontrado nenhum setor");
        }
        return setorRepository.deleteById(id);
    }
}
