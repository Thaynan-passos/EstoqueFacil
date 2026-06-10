package service;

import exceptions.TelefoneInvalidoException;
import model.TelefoneModel;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    public static void numeroTelefoneValidar(String numerotelefone) {

        String numeroLimpo = numerotelefone.replaceAll("[^0-9]", "");


        if (!numeroLimpo.matches("^\\d{11}$")) {

          throw new TelefoneInvalidoException("Atenção: O telefone deve contar 11 digitos (DDD + número)!");
        }

        int ddd = Integer.parseInt(numeroLimpo.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
           throw new TelefoneInvalidoException("Atenção: DDD inválido. Deve estar entre 11 e 99.");
        }
    }

}


