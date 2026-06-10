package Utils;

import model.FuncionarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioSenhaService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrarFuncionario(FuncionarioModel usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenhaHash());
        usuario.setSenhaHash(senhaCriptografada);
        
    }
}
