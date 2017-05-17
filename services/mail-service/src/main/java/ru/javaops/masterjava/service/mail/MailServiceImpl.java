package ru.javaops.masterjava.service.mail;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService")
public class MailServiceImpl implements MailService {
    @Override
    public void sendEmail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        MailSender.sendMail(to, cc, subject, body);
    }
}