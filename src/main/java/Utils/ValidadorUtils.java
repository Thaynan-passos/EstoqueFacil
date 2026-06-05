package Utils;


import exceptions.CampoPreenchimento;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Validated
public class ValidadorUtils {

    public static void validarEmail(String email) {

        String emailSemEspaco = email.replace(" ", "");

        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new CampoPreenchimento("O formato do seu email está equivocado");
        }

    }

    public static void validarCPF(String cpf) {
        System.out.println("--- VALIDADOR CHAMADO --- O CPF RECEBIDO FOI: [" + cpf + "]");

        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(cpf);

        if (!matcher.matches()) {
            throw new CampoPreenchimento("O formato do cpf está equivocado");
        }

        String cpfSemEspaco = cpf.replaceAll("\\D", "");

        if (cpfSemEspaco.length() != 11) {
            throw new CampoPreenchimento("O seu cpf deve possuir 11 dígitos");
        }

        if (cpfSemEspaco.matches("(\\d)\\1{10}")) {
            throw new CampoPreenchimento("CPF inválido");
        }

        int dig10, dig11;
        int sm, i, r, num, peso;

        // --- CÁLCULO DO 1º DÍGITO VERIFICADOR ---
        sm = 0;
        peso = 10;
        for (i = 0; i < 9; i++) {
            num = Integer.parseInt(cpfSemEspaco.substring(i, i + 1));
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = sm % 11;
        if (r < 2) {
            dig10 = 0; // Se o resto for 0 ou 1, o dígito é 0
        } else {
            dig10 = 11 - r; // Se for de 2 a 10, subtrai de 11
        }

        // --- CÁLCULO DO 2º DÍGITO VERIFICADOR ---
        sm = 0;
        peso = 11;
        for (i = 0; i < 10; i++) {
            num = Integer.parseInt(cpfSemEspaco.substring(i, i + 1));
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = sm % 11;
        if (r < 2) {
            dig11 = 0; // Se o resto for 0 ou 1, o dígito é 0
        } else {
            dig11 = 11 - r; // Se for de 2 a 10, subtrai de 11
        }

        // 4. Extração dos dígitos informados no CPF para comparação
        int digito10Real = Integer.parseInt(cpfSemEspaco.substring(9, 10));
        int digito11Real = Integer.parseInt(cpfSemEspaco.substring(10, 11));

        // 5. Confronto numérico final
        if ((dig10 != digito10Real) || (dig11 != digito11Real)) {
            throw new CampoPreenchimento("O CPF informado é inválido (dígitos verificadores não conferem).");
        }
    }

    public static void validarCNPJ(String cnpj) {

        String regex = "^[a-zA-Z0-9]{2}\\.[a-zA-Z0-9]{3}\\.[a-zA-Z0-9]{3}/[a-zA-Z0-9]{4}-\\d{2}$|^[a-zA-Z0-9]{12}\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cnpj);

        if (!matcher.matches()) {
            throw new CampoPreenchimento("O formato do seu CNPJ está equivocado");
        }

        String cnpjSemEspaco = cnpj.replaceAll("[^a-zA-Z0-9]", "");

        if (cnpjSemEspaco.length() != 14) {
            throw new CampoPreenchimento("O seu CNPJ deve possuir 14 dígitos");
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        sm = 0;
        peso = 2;
        for (i = 11; i >= 0; i--) {
            num = valorCaracter(cnpjSemEspaco.charAt(i));
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10) peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1)) dig13 = '0';
        else dig13 = (char) ((11 - r) + 48);

        sm = 0;
        peso = 2;
        for (i = 12; i >= 0; i--) {
            num = valorCaracter(cnpjSemEspaco.charAt(i));
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10) peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1)) dig14 = '0';
        else dig14 = (char) ((11 - r) + 48);

        if ((dig13 != cnpjSemEspaco.charAt(12)) || (dig14 != cnpjSemEspaco.charAt(13))) {
            throw new CampoPreenchimento("O CNPJ informado é inválido.");
        }

    }

    private static int valorCaracter(char c) {

        if (Character.isDigit(c)) {
            return c - '0';
        }
        return Character.toUpperCase(c) - 'A' + 10;
    }
}

