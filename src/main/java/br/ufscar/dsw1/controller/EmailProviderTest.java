package br.ufscar.dsw1.controller;

import br.ufscar.dsw1.utils.EmailService;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmailProviderTest", value = "/mailtest")
public class EmailProviderTest extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        EmailService service = new EmailService();

        InternetAddress from = new InternetAddress("viniciusluz@estudante.ufscar.br", "Caixa Econômica Federal");
        InternetAddress to = new InternetAddress("viniciusluz@estudante.ufscar.br", "Vinicius");

        String subject1 = "bom dia favor quitar cartão contato: 11 99123-456";
        String subject2 = "Exemplo Subject com Anexo (Mailtrap/Java)";

        String body1 = "Exemplo mensagem (Mailtrap/Java)";
        String body2 = "Exemplo mensagem com Anexo (Mailtrap/Java)";

        // Envio sem anexo
        service.send(from, to, subject1, body1);

        // Envio com anexo
//        service.send(from, to, subject2, body2);
    }
}
