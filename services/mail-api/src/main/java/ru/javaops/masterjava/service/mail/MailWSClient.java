package ru.javaops.masterjava.service.mail;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.io.Resources;
import java.util.List;
import javax.xml.ws.soap.MTOMFeature;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import ru.javaops.web.AuthUtil;
import ru.javaops.web.WebStateException;
import ru.javaops.web.WsClient;

import javax.xml.namespace.QName;
import java.util.Set;
import ru.javaops.web.handler.SoapClientLoggingHandler;

@Slf4j
public class MailWSClient {
    private static final WsClient<MailService> WS_CLIENT;
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    private static final SoapClientLoggingHandler LOGGING_HANDLER = new SoapClientLoggingHandler(Level.DEBUG);

    public static final String AUTH_HEADER = AuthUtil.encodeBasicAuth(USER, PASSWORD);

    static {
        WS_CLIENT = new WsClient<MailService>(Resources.getResource("wsdl/mailService.wsdl"),
                new QName("http://mail.javaops.ru/", "MailServiceImplService"),
                MailService.class);

        WS_CLIENT.init("mail", "/mail/mailService?wsdl");
    }

    public static String sendBulkMail(final Set<Addressee> to, final Set<Addressee> cc, final String subject, final String body, final List<Attach> attaches) throws WebStateException {
        log.info("Send mail to '" + to + "' cc '" + cc + "' subject '" + subject + (log.isDebugEnabled() ? "\nbody=" + body : ""));
        String status;
        try {
            status = getPort().sendBulkMail(to, cc, subject, body, attaches);
            log.info("Sent with status: " + status);
        } catch (Exception e) {
            throw WsClient.getWebStateException(e);
        }
        return status;
    }

    public static GroupResult sendIndividualMails(final Set<Addressee> to, final String subject, final String body, final List<Attach> attaches) throws WebStateException {
        log.info("Send mail to '" + to + "' subject '" + subject + (log.isDebugEnabled() ? "\nbody=" + body : ""));
        GroupResult result;
        try {
            result = getPort().sendIndividualMails(to, subject, body, attaches);
        } catch (WebStateException e) {
            throw WsClient.getWebStateException(e);
        }
        log.info("Sent with result: " + result);
        return result;
    }

    private static MailService getPort() {
        MailService port = WS_CLIENT.getPort(/*new MTOMFeature(1024)*/);
        WsClient.setAuth(port, USER, PASSWORD);
        WsClient.setHandler(port, LOGGING_HANDLER);
        return port;
    }

    public static Set<Addressee> split(String addressees) {
        Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings().split(addressees);
        return ImmutableSet.copyOf(Iterables.transform(split, Addressee::new));
    }
}
