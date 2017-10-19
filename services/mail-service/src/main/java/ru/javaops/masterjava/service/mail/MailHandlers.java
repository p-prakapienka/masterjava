package ru.javaops.masterjava.service.mail;

import ru.javaops.web.handler.Handlers;
import ru.javaops.web.handler.SoapServerSecurityHandler;

public class MailHandlers {
    public static class MailSecurityHandler extends SoapServerSecurityHandler {
        public MailSecurityHandler() {
            super(MailWSClient.getHostConfig().authHeader);
        }
    }

    public static class MailLoggingHandler extends Handlers.SoapServerLoggingHandler {
        public MailLoggingHandler() {
            super(MailWSClient.getHostConfig().serverDebugLevel);
        }
    }
}
