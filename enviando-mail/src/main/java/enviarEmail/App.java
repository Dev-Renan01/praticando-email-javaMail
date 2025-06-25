package enviarEmail;

import org.junit.jupiter.api.Test;


public class App {


    @Test
    public void testeEmail()throws Exception{

        StringBuilder stringBuilderEmail = new StringBuilder();
        stringBuilderEmail.append("Olá, <br/>");
        stringBuilderEmail.append("Você está recebendo o acesso ao meu github -> 'Dev-Renan'<br/>");
        stringBuilderEmail.append("para ter acesso clique no botão abaixo!<br/>");

        stringBuilderEmail.append("<a target=\"_blank\" href=\"https://github.com/Dev-Renan01\" style=\"color:#2525a7; padding: 14px 25px; text-align:center; text-decoration: none; display:inline-block; border-radius:30px; font-size: 20px; font-family: Courier, sans-serif; border: 3px solid green;\">Acesse o meu github!</a><br/>");
        stringBuilderEmail.append("<a target=\"_blank\" href=\"https://github.com/Dev-Renan01\">Botão 02 github</a>");


        ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("tjava4720@gmail.com"
                ,"Dev-Renan"
                , "Testando e-mail com java"
                ,stringBuilderEmail.toString());

        enviaEmail.EnviarEmail(true);


       Thread.sleep(5000);
    }
}