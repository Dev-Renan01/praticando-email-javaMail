package enviando.email;

import org.junit.jupiter.api.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class App {

    private String userName = "tjava4720@gmail.com" ;
    private String senha = "k n z v k s q i u l d l e g t o";

    @Test
    public void testeEmail(){
        /*SMTP - protocolo padrão para envio de E-mail*/

        Properties properties = new Properties();//Armazena pares chave-valor de configuração

        try {
            properties.put("mail.smtp.auth", "true");/*autorização*/
            properties.put("mail.smtp.starttls", "true");/*Autenticação*/
            properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor Gmail Google*/
            properties.put("mail.smtp.port", "465");/*Porta do servidor*/
            properties.put("mail.smtp.socketFactory.port", "465");/*Especifica se a porta a ser conectada pelo socket*/
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Clase socket de conecão ao SMTP*/

            Session session = Session.getInstance(properties, new Authenticator() {//Cria uma sessão de e-mail autenticada,
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, senha);
                }
            });

            Address[] toUser = InternetAddress.parse("tjava4720@gmail.com");//estabelecer os destinatários

            Message mensagem = new MimeMessage(session);// enviar a mensagem
            mensagem.setFrom(new InternetAddress(userName));// Quem está enviando
            mensagem.setRecipients(Message.RecipientType.TO, toUser);// e-mail de destino
            mensagem.setSubject("Chegou o e-mail enviado com java!");//Assunto do e-mail
            mensagem.setText("olá programador, você acaba de receber  um  e-mail enviado com java!");//Corpo do e-mail

            Transport.send(mensagem);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}