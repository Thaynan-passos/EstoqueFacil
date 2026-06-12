package service;

import exceptions.ErroDePreenchimentoInvalidoException;
import model.MovimentacaoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MovimentacaoService {

    public void validarDataMovimentacao(LocalDate dataMovimentacao) {
        if (dataMovimentacao.isAfter(LocalDate.now())) {
            throw new ErroDePreenchimentoInvalidoException("A data de movimentação não pode ser uma data futura.");
        }
    }

    public void validarMovimentacao(MovimentacaoModel movimentacao) {
        validarDataMovimentacao(movimentacao.getDataMovimentacao());
    }

}
