package com.jma.productoservice.email.application;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public String sendTextEmail( String receptorEmail) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email("i202010528@cibertec.edu.pe");
        Email to = new Email(receptorEmail);
        Content content = new Content("text/plain", "Fue enviado una orden");
        Mail mail = new Mail(from, "Recepcionar Orden", to, content);

        SendGrid sg = new SendGrid("");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
