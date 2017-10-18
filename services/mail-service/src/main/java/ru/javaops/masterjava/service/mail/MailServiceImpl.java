package ru.javaops.masterjava.service.mail;

import com.sun.xml.ws.developer.StreamingAttachment;
import java.util.List;
import javax.xml.ws.soap.MTOM;
import ru.javaops.web.WebStateException;

import javax.jws.WebService;
import java.util.Set;

@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService", targetNamespace = "http://mail.javaops.ru/"
//          , wsdlLocation = "WEB-INF/wsdl/mailService.wsdl"
)
//@StreamingAttachment(parseEagerly = true, memoryThreshold = 1024)
//@MTOM
public class MailServiceImpl implements MailService {
    private final MailServiceExecutor mailServiceExecutor = new MailServiceExecutor();

    @Override
    public String sendBulkMail(Set<Addressee> to, Set<Addressee> cc, String subject, String body, List<Attach> attaches) throws WebStateException {
        return MailSender.sendMail(to, cc, subject, body, attaches);
    }

    @Override
    public GroupResult sendIndividualMails(Set<Addressee> to, String subject, String body, List<Attach> attaches) throws WebStateException {
        return mailServiceExecutor.sendToList(to, subject, body, attaches);
    }
}
