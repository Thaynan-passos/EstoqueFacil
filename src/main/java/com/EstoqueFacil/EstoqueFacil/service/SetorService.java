package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.dao.SetorDAO;

import com.EstoqueFacil.EstoqueFacil.model.SetorModel;

import com.EstoqueFacil.EstoqueFacil.model.TelefoneModel;
import exceptions.CampoPreenchimento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SetorService {

    private final SetorDAO setorDAO;

    public SetorService(SetorDAO setorDAO){
        this.setorDAO = setorDAO;
    }

    public SetorModel cadastrarSetor(SetorModel setorModel){

        return setorDAO.save(setorModel);
    }

    public SetorModel buscarPorId(int id) {

        return setorDAO.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum telefone foi encontrado"));
    }


    public List<SetorModel> buscarTodosSetores() {

        return this.setorDAO.findAll();
    }

    public SetorModel atualizarSetorPorId(int id, SetorModel dadosAtualizado) {

       SetorModel setorNovo = buscarPorId(id);



       setorNovo.setCapacidade(dadosAtualizado.getCapacidade());
       setorNovo.setOrcamentoMensal(dadosAtualizado.getOrcamentoMensal());
       setorNovo.setNomeSetor(dadosAtualizado.getNomeSetor());

       return setorDAO.save(setorNovo);
    }

    public void deletarSetorPorId(int id){

        if(!setorDAO.existsById(id)){
            throw new NoSuchElementException("Não foi encontrado nenhum setor");
        }

    }
}
