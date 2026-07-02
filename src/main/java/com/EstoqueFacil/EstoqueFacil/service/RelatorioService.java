package com.EstoqueFacil.EstoqueFacil.service;


import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.EstoqueFacil.EstoqueFacil.model.Relatorio;
import com.EstoqueFacil.EstoqueFacil.repository.RelatorioRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import exceptions.ErroDePreenchimentoInvalidoException;


@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

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

    public Relatorio cadastrarRelatorio(Relatorio relatorioModel) {
        validarRelatorio(relatorioModel);
        return relatorioRepository.save(relatorioModel);
    }

    public byte[] gerarPdf() {

        List<Relatorio> relatorios = relatorioRepository.findAll();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Document doc = new Document();
            PdfWriter.getInstance(doc, out);

            doc.open();


            doc.add(new Paragraph("RELATÓRIO FINANCEIRO DO ESTOQUE"));
            doc.add(new Paragraph(" "));

            for (Relatorio r : relatorios) {

                doc.add(new Paragraph("==================================="));

                doc.add(new Paragraph("ID: " + r.getIdRelatorio()));
                doc.add(new Paragraph("Descrição: " + r.getDescricao()));
                doc.add(new Paragraph("Data emissão: " + r.getDataEmissao()));
                doc.add(new Paragraph("Período: " + r.getDataInicio() + " até " + r.getDataFim()));

                doc.add(new Paragraph("Valor total entradas: R$ " + r.getValorTotalEntrada()));
                doc.add(new Paragraph("Valor total saídas: R$ " + r.getValorTotalSaida()));

                if (r.getFuncionario() != null) {
                    doc.add(new Paragraph("Emitido por: " + r.getFuncionario().getNome()));
                }

                doc.add(new Paragraph(" "));
            }

            doc.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    public Relatorio buscarPorId(Integer id){
        return relatorioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum relatório foi encontrado"));
    }

    public Relatorio buscarPorDataEmissao(LocalDate dataEmissao){

         return relatorioRepository.findByDataEmissao(dataEmissao).orElseThrow(() -> new NoSuchElementException("Nenhum relatório foi encontrado."));
    }

    public List<Relatorio> buscarTodosRelatorios() {
        return relatorioRepository.findAll();
    }


    public Relatorio atualizarPorDataEmitida(LocalDate dataEmissao, Relatorio dadosAtualizados) {

        Relatorio relatorioNovo = buscarPorDataEmissao(dataEmissao);

        relatorioNovo.setDataFim(dadosAtualizados.getDataFim());
        relatorioNovo.setDescricao(dadosAtualizados.getDescricao());

        return relatorioRepository.save(relatorioNovo);
    }

    public Relatorio deletarRelatorioPorId(int id){
        if(!relatorioRepository.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhum relatório com o id " + id);
        }

        Relatorio relatorio = relatorioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Não existe nenhum relatório com o id " + id));

        relatorioRepository.deleteById(id);

        return relatorio;
    }

    public void validarRelatorio(Relatorio relatorio){
        validarDataEmissao(relatorio.getDataEmissao());
        validarDataCriacao(relatorio.getDataInicio());
        validarDataFim(relatorio.getDataFim(), relatorio.getDataInicio());
    }

}
