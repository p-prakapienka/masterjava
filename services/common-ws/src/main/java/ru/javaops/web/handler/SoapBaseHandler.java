package ru.javaops.web.handler;

import com.sun.xml.ws.api.handler.MessageHandler;
import com.sun.xml.ws.api.handler.MessageHandlerContext;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;

public abstract class SoapBaseHandler implements MessageHandler<MessageHandlerContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public void close(MessageContext context) {

    }

    protected static boolean isOutbound(MessageHandlerContext context) {
        return (Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    }
}
