package com.EstoqueFacil.EstoqueFacil.exception;

import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import exceptions.TelefoneInvalidoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handler global de exceptions para controllers baseados em Thymeleaf (retorno de views).
 * Captura as exceptions de validação/negócio antes que virem stack trace de 500 no log,
 * e redireciona o usuário de volta para a página de origem com uma mensagem de erro amigável.
 *
 * Como funciona:
 * - Usa o header "Referer" da requisição para saber pra qual página redirecionar de volta.
 * - Anexa a mensagem de erro como RedirectAttributes (flash attribute) chamada "erro".
 * - Nas suas views Thymeleaf, basta ter algo como:
 *
 *   <div th:if="${erro}" class="alert alert-danger" th:text="${erro}"></div>
 *
 * para exibir a mensagem após o redirect.
 */
@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {

    /**
     * Exceptions de validação de negócio (senha inválida, telefone inválido,
     * CNPJ/nome inválido, etc). Todas retornam uma mensagem amigável direta.
     */
    @ExceptionHandler({
            ErroDePreenchimentoInvalidoException.class,
            TelefoneInvalidoException.class,
            CampoPreenchimento.class
    })
    public String handleErroDeValidacao(RuntimeException ex, HttpServletRequest request,
                                        RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("erro", ex.getMessage());
        return "redirect:" + getReferer(request);
    }

    /**
     * Validações do Bean Validation (jakarta.validation) que escaparem até aqui
     * sem passar pelo validador manual do service (ex.: em outro fluxo que não passe
     * pelo FornecedorService.validarFornecedor).
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request,
                                            RedirectAttributes redirectAttributes) {
        String mensagens = ex.getConstraintViolations().stream()
                .map(v -> v.getMessage())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Dados inválidos.");
        redirectAttributes.addFlashAttribute("erro", mensagens);
        return "redirect:" + getReferer(request);
    }

    /**
     * Validação de formulário anotado com @Valid em @ModelAttribute/@RequestBody.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request,
                                               RedirectAttributes redirectAttributes) {
        String mensagens = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getDefaultMessage())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Dados inválidos.");
        redirectAttributes.addFlashAttribute("erro", mensagens);
        return "redirect:" + getReferer(request);
    }

    /**
     * Erro de conversão de tipo no parâmetro (ex.: numeroLote como String "20260710001"
     * não cabendo em Integer, ou qualquer outro mismatch de tipo em @RequestParam/@PathVariable).
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request,
                                     RedirectAttributes redirectAttributes) {
        String mensagem = String.format(
                "Valor inválido para o campo '%s': '%s'.",
                ex.getName(), ex.getValue());
        redirectAttributes.addFlashAttribute("erro", mensagem);
        return "redirect:" + getReferer(request);
    }

    /**
     * Fallback genérico — qualquer outra RuntimeException não tratada especificamente
     * cai aqui, evitando expor stack trace pro usuário final. Loga o erro real no console
     * pra facilitar debug, mas mostra mensagem genérica na tela.
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleGenericException(RuntimeException ex, HttpServletRequest request,
                                         RedirectAttributes redirectAttributes, Model model) {
        ex.printStackTrace(); // mantém o log no console para debug
        redirectAttributes.addFlashAttribute("erro", "Ocorreu um erro inesperado. Tente novamente.");
        return "redirect:" + getReferer(request);
    }

    private String getReferer(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return (referer != null && !referer.isBlank()) ? referer : "/";
    }
}