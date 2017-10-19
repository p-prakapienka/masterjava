package ru.javaops.masterjava.service.mail.rest;

import java.util.logging.Handler;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class MailRestConfig extends ResourceConfig {

    public MailRestConfig() {
        // Set Jersey log to SLF4J instead of JUL
        // http://stackoverflow.com/questions/4121722

        java.util.logging.Logger rootLogger =
            java.util.logging.LogManager.getLogManager().getLogger("");
        java.util.logging.Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            rootLogger.removeHandler(handler);
        }
        org.slf4j.bridge.SLF4JBridgeHandler.install();

        packages("ru.javaops.masterjava.service.mail");
    }
}
