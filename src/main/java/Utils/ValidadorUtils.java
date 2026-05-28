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

        char dig10, dig11;
        int sm, i, r, num, peso;

        sm = 0;
        peso = 10;
        for (i = 0; i < 9; i++) {
            num = (int) (cpfSemEspaco.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) dig10 = '0';
        else dig10 = (char) (r + 48);

        sm = 0;
        peso = 11;
        for (i = 0; i < 10; i++) {
            num = (int) (cpfSemEspaco.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) dig11 = '0';
        else dig11 = (char) (r + 48);

        if ((dig10 != cpfSemEspaco.charAt(9)) || (dig11 != cpfSemEspaco.charAt(10))) {
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

