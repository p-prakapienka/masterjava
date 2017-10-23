package ru.javaops.masterjava.service.mail.jms;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class JmsListener implements ServletContextListener {
    private Thread listenerThread = null;
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            InitialContext initCtx = new InitialContext();
            log.info(initCtx.lookup("jms/ConnectionFactory").getClass().getCanonicalName());
            ConnectionFactory connectionFactory =
                (ConnectionFactory)initCtx.lookup("jms/ConnectionFactory");
            connection = connectionFactory.createConnection();
            Session queueSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue)initCtx.lookup("jms/queue/MailQueue");
            MessageConsumer receiver = queueSession.createConsumer(queue);
            connection.start();
            log.info("Listen JMS messages ...");
            listenerThread = new Thread(() -> {
                try {
                    while (!Thread.interrupted()) {
                        Message m = receiver.receive();
                        // TODO implement mail sending
                        if (m instanceof TextMessage) {
                            TextMessage tm = (TextMessage)m;
                            String text = tm.getText();
                            log.info("Received TextMessage with text {}.", text);
                        }
                    }
                } catch (Exception e) {
                    log.error("Receiving messages failed: " + e.getMessage(), e);
                }
            });
            listenerThread.start();
        } catch (Exception e) {
            log.error("JMS failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException ex) {
                log.warn("Couldn't close JMSConnection: ", ex);
            }
        }
        if (listenerThread != null) {
            listenerThread.interrupt();
        }
    }
}
