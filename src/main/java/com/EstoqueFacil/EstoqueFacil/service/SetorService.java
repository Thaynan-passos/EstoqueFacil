package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.model.Setor;
import com.EstoqueFacil.EstoqueFacil.repository.SetorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository){
        this.setorRepository = setorRepository;
    }

    public Setor cadastrarSetor(Setor setor) {
        return setorRepository.save(setor);
    }

    public Setor buscarPorId(int id) {
        return setorRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Nenhum setor foi encontrado com ID: " + id)
                );
    }

    public List<Setor> buscarTodosSetores() {
        return setorRepository.findAll();
    }

    public Setor atualizarSetorPorId(int id, Setor dadosAtualizado) {

        Setor setor = buscarPorId(id);

        setor.setCapacidade(dadosAtualizado.getCapacidade());
        setor.setOrcamentoMensal(dadosAtualizado.getOrcamentoMensal());
        setor.setNomeSetor(dadosAtualizado.getNomeSetor());

        return setorRepository.save(setor);
    }

    public void deletarSetorPorId(int id) {

        if (!setorRepository.existsById(id)) {
            throw new NoSuchElementException("Não foi encontrado nenhum setor com ID: " + id);
        }

        setorRepository.deleteById(id);
    }
}