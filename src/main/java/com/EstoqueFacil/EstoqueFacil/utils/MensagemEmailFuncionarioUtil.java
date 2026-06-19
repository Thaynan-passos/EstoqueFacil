package com.EstoqueFacil.EstoqueFacil.utils;

import jakarta.mail.Authenticator;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MensagemEmailFuncionarioUtil {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private String port;

    @Value("${mail.smtp.username}")
    private String remetente;

    @Value("${mail.smtp.password}")
    private String senha;

    public void enviarConfirmacao(String emailUsuario, String nomeUsuario) {

        // Propriedades do Servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //  Criar a Sessão de Autenticação
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            //  Criar a Mensagem
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailUsuario));
            message.setSubject("Confirmação de Cadastro Realizada!");

            // O conteúdo da mensagem
            String corpoEmail = nomeUsuario + ",\n\n"
                    + "Este é um e-mail automático para confirmar que seu cadastro foi concluído.";
            message.setText(corpoEmail);

            //  Enviar o e-mail
            Transport.send(message);

            System.out.println("E-mail enviado com sucesso para: " + emailUsuario);

        } catch (MessagingException e) {
            System.err.println("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}