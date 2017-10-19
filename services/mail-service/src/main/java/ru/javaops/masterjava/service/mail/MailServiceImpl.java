package ru.javaops.masterjava.service.mail;

import com.sun.xml.ws.developer.StreamingAttachment;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.MTOM;
import ru.javaops.web.AuthUtil;
import ru.javaops.web.WebStateException;

import javax.jws.WebService;
import java.util.Set;

@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService", targetNamespace = "http://mail.javaops.ru/"
//          , wsdlLocation = "WEB-INF/wsdl/mailService.wsdl"
)
//@StreamingAttachment(parseEagerly = true, memoryThreshold = 1024)
//@MTOM
@HandlerChain(file = "mailWsHandlers.xml")
public class MailServiceImpl implements MailService {

//    @Resource
//    private WebServiceContext wsContext;

    @Override
    public String sendBulkMail(Set<Addressee> to, Set<Addressee> cc, String subject, String body, List<Attach> attaches) throws WebStateException {
        /*MessageContext mCtx = wsContext.getMessageContext();
        Map<String, List<String>> headers = (Map<String, List<String>>)mCtx.get(MessageContext.HTTP_REQUEST_HEADERS);
        HttpServletRequest request = (HttpServletRequest)mCtx.get(MessageContext.SERVLET_REQUEST);
        HttpServletResponse response = (HttpServletResponse) mCtx.get(MessageContext.SERVLET_RESPONSE);

        int code = AuthUtil.checkBasicAuth(headers, MailWSClient.AUTH_HEADER);
        if (code != 0) {
            mCtx.put(MessageContext.HTTP_RESPONSE_CODE, code);
            throw new SecurityException();
        }*/
        return MailSender.sendMail(to, cc, subject, body, attaches);
    }

    @Override
    public GroupResult sendIndividualMails(Set<Addressee> to, String subject, String body, List<Attach> attaches) throws WebStateException {
        return MailServiceExecutor.sendToList(to, subject, body, attaches);
    }
}
