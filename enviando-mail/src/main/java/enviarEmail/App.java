package enviarEmail;

import org.junit.jupiter.api.Test;


public class App {


    @Test
    public void testeEmail()throws Exception{

        ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("tjava4720@gmail.com, suanysouza.contato@gmail.com"
                ,"Dev-Renan"
                , "Testando e-mail com java"
        ,"Esse texto é a descrição do meu e-mail");

        enviaEmail.EnviarEmail();


       Thread.sleep(5000);
    }
}