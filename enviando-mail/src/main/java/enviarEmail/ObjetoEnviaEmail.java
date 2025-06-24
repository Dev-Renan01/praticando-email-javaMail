package enviarEmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ObjetoEnviaEmail {

    private String userName = "tjava4720@gmail.com" ;
    private String senha = "k n z v k s q i u l d l e g t o";

    private String listaDestinatarios = "";
    private String nomeRemetente = "";
    private String assuntoEmail = "";
    private String textoEmail = "";

    public ObjetoEnviaEmail(String listaDestinatarios, String nomeRemetente,
                            String assuntoEmail, String textoEmail) {

        this.listaDestinatarios = listaDestinatarios;
        this.nomeRemetente = nomeRemetente;
        this.assuntoEmail = assuntoEmail;
        this.textoEmail = textoEmail;
    }

    public void  EnviarEmail()throws Exception{

        Properties properties = new Properties();//Armazena pares chave-valor de configuração

        properties.put("mail.smtp.ssl.trust", "*");
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

        Address[] toUser = InternetAddress.parse(listaDestinatarios);//estabelecer os destinatários

        Message mensagem = new MimeMessage(session);// enviar a mensagem
        mensagem.setFrom(new InternetAddress(userName, nomeRemetente));// Quem está enviando
        mensagem.setRecipients(Message.RecipientType.TO, toUser);// e-mail de destino
        mensagem.setSubject(assuntoEmail);//Assunto do e-mail
        mensagem.setText(textoEmail);//Corpo do e-mail

        Transport.send(mensagem);


    }
}

