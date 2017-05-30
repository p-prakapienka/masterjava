package ru.javaops.masterjava.service.mail;

import ru.javaops.web.WebStateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Set;

@WebService(targetNamespace = "http://mail.javaops.ru/")
//@SOAPBinding(
//        style = SOAPBinding.Style.DOCUMENT,
//        use = SOAPBinding.Use.LITERAL,
//        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface MailService {

    @WebMethod
    String sendBulkMail(
            @WebParam(name = "to") Set<Addressee> to,
            @WebParam(name = "cc") Set<Addressee> cc,
            @WebParam(name = "subject") String subject,
            @WebParam(name = "body") String body) throws WebStateException;

    @WebMethod
    GroupResult sendIndividualMails(
            @WebParam(name = "to") Set<Addressee> to,
            @WebParam(name = "subject") String subject,
            @WebParam(name = "body") String body) throws WebStateException;
}
