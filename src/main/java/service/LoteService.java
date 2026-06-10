package service;

import exceptions.ErroDePreenchimentoInvalidoException;
import model.LoteModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoteService {


    public void validarDataValida(LocalDate data){
          if (data.isBefore(LocalDate.now())) {

            throw new ErroDePreenchimentoInvalidoException("Error: Não tem como o produto já estar vencido!");
        }
    }

    public void validarDataFabricacao(LocalDate dataFabricacao) {

        if (dataFabricacao.isAfter(LocalDate.now())) {
            throw new ErroDePreenchimentoInvalidoException("A data de fabricação não pode ser uma data futura.");
        }
    }

    public void validarDataFornecimento(LocalDate dataFornecimento, LocalDate dataFabricacao) {

        if (dataFornecimento.isBefore(dataFabricacao)) {
            throw new ErroDePreenchimentoInvalidoException("A data de fornecimento não pode ser anterior à data de fabricação.");
        }
    }

    public void validarLote(LoteModel lote) {
        validarDataValida(lote.getDataValidade());
        validarDataFabricacao(lote.getDataFabricacao());
        validarDataFornecimento(lote.getDataFornecimento(), lote.getDataFabricacao());
    }

}
