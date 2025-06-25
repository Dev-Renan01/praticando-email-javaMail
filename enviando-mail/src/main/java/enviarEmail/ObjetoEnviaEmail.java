package enviarEmail;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    public void  EnviarEmail(Boolean envioHtml)throws Exception {

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

        if (envioHtml) {
            mensagem.setContent(textoEmail, "text/html; charset=utf-8");//Conteúdo e tipo
        } else {
            mensagem.setText(textoEmail);//Corpo do e-mail
        }

        Transport.send(mensagem);
    }



    public void  EnviarEmailAnexo (Boolean envioHtml)throws Exception {

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


        //Parte 01 do e-mail que é texto e a descrição do e-mail
        MimeBodyPart corpoEmail = new MimeBodyPart();

        if (envioHtml) {
            corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");//Conteúdo e tipo
        } else {
            corpoEmail.setText(textoEmail);//Corpo do e-mail
        }

        //Parte 02 do e-mail que são os anexos em PDF
        MimeBodyPart anexoEmail = new MimeBodyPart();

        anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePDF(), "Application/pdf")));
        anexoEmail.setFileName("Anexoemail.pdf");


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(corpoEmail);
        multipart.addBodyPart(anexoEmail);

        mensagem.setContent(multipart);

        Transport.send(mensagem);
    }

        /*Simular o PDF ou qualquer arquivo que possa ser enviado por anexo no email.
        Pode pega o arquivo no banco de dados. */

        private FileInputStream simuladorDePDF() throws Exception {
            Document document = new Document();

            File file = new File("fileanexo.pdf");
            file.createNewFile();

            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();
            document.add(new Paragraph("Conteúdo do PDF anexo com Java Mail, esse texto é do PDF."));
            document.close();

            document.close();

            return new FileInputStream(file);
        }
}