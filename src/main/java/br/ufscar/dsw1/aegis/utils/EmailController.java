package br.ufscar.dsw1.aegis.utils;

import br.ufscar.dsw1.domain.User;
import br.ufscar.dsw1.utils.EmailService;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

public class EmailController {
    public static void sendPasswordResetToken(User user, String path) throws UnsupportedEncodingException {
        EmailService service = new EmailService();

        InternetAddress from = new InternetAddress("no-reply@debatr.com", "Debatr");
        InternetAddress to = new InternetAddress(user.getEmail(), user.getName());

        String subject = "Pedido de alteração de senha";

        final String token = JWTManager.generateJWTToken(user);

        //language=HTML
        String body = "<h1>Pedido de alteração de senha em sua conta do Debatr</h1>" +
                "<p>Recebemos um pedido para alterar a senha da sua conta. Clique no link abaixo para prosseguir.</p> <br>" +
                "<a href='" + path + "/reset-password?token=" + token + "'>Clique aqui para alterar sua senha</a>";

        service.send(from, to, subject, body);
    }
}
