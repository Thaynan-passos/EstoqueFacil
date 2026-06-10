package service;


import exceptions.ErroDePreenchimentoInvalidoException;
import model.RelatorioModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class RelatorioService {

    public void validarDataEmissao(LocalDate dataEmissao) {

        if (!dataEmissao.isEqual(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data da emissão deve se referir apenas a hoje");
        }
    }

    public  void validarDataCriacao(LocalDate dataInicio) {

        if(dataInicio.isBefore(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data não pode ser antes do dia atual");
        }
    }

    public void validarDataFim(LocalDate dataFim, LocalDate dataInicio) {

        if(dataFim.isBefore(dataInicio)) throw new ErroDePreenchimentoInvalidoException("O fim não pode ser antes do início");
    }

    public void validarRelatorio(RelatorioModel relatorio){
        validarDataEmissao(relatorio.getDataEmissao());
        validarDataCriacao(relatorio.getDataInicio());
        validarDataFim(relatorio.getDataFim(), relatorio.getDataInicio());
    }

}
