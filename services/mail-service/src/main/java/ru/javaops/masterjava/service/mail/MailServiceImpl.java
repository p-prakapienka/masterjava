package ru.javaops.masterjava.service.mail;

import ru.javaops.web.WebStateException;

import javax.jws.WebService;
import java.util.Set;

@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService", targetNamespace = "http://mail.javaops.ru/"
//          , wsdlLocation = "WEB-INF/wsdl/mailService.wsdl"
)
public class MailServiceImpl implements MailService {
    private final MailServiceExecutor mailServiceExecutor = new MailServiceExecutor();

    @Override
    public String sendBulkMail(Set<Addressee> to, Set<Addressee> cc, String subject, String body) throws WebStateException {
        return MailSender.sendMail(to, cc, subject, body);
    }

    @Override
    public GroupResult sendIndividualMails(Set<Addressee> to, String subject, String body) throws WebStateException {
        return mailServiceExecutor.sendToList(to, subject, body);
    }
}
