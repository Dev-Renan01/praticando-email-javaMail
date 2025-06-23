package enviando.email;

import org.junit.jupiter.api.Test;

import java.util.Properties;

public class App {

    @Test
    public void testeEmail(){
        /*SMTP - protocolo padrão para envio de E-mail*/

        Properties properties = new Properties();
        /*autorização*/
        properties.put("mail.smtp.auth", "true");// Hash Map, contém chave e valor / key, value

        /*Autenticação*/
        properties.put("mail.smtp.starttls", "true"); // Garantiar a segurança da conexão entre a aplicação

        /*Servidor Gmail Google*/
        properties.put("mail.smtp.host.", "smtp.gmail.com");//Enviar os e-mails através do servidor de envio de e-mails do Gmail.

        /*Porta do servidor*/
        properties.put("mail.smtp.port", "465");//Caminho do servidor / A porta de rede usada para se conectar ao servidor de envio de e-mails (SMTP).

        /*Especifica se a porta a ser conectada pelo socket*/
        properties.put("mail.smtp.socketFactory.port", "465");// Define a porta 465 para conexões SMTP

        /*Clase socket de conecão ao SMTP*/
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        

    }

}
