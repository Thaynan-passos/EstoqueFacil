package service;


import exceptions.ErroDePreenchimentoInvalidoException;
import model.RequisicaoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RequisicaoService {

    public void validarDataRequisicao(LocalDate date) {

        if(date.isAfter(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data deve ser posterior a hoje");
        }
    }

    public void validarRequisicao(RequisicaoModel requisicao) {
        validarDataRequisicao(requisicao.getDataRequisicao());
    }

}
