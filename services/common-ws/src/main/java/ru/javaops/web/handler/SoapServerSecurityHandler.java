package ru.javaops.web.handler;

import static ru.javaops.web.AuthUtil.encodeBasicAuthHeader;

import com.sun.xml.ws.api.handler.MessageHandlerContext;
import java.util.List;
import java.util.Map;
import javax.xml.ws.handler.MessageContext;
import lombok.extern.slf4j.Slf4j;
import ru.javaops.web.AuthUtil;

@Slf4j
public class SoapServerSecurityHandler extends SoapBaseHandler {

    private String authHeader;

    public SoapServerSecurityHandler(String authHeader) {
        this.authHeader = authHeader;
    }

    public SoapServerSecurityHandler(String user, String password) {
        this(encodeBasicAuthHeader(user, password));
    }

    @Override
    public boolean handleMessage(MessageHandlerContext ctx) {
        if (!isOutbound(ctx)) {
            Map<String, List<String>> headers = (Map<String, List<String>>)ctx.get(MessageContext.HTTP_REQUEST_HEADERS);
            int code = AuthUtil.checkBasicAuth(headers, authHeader);
            if (code != 0) {
                ctx.put(MessageContext.HTTP_RESPONSE_CODE, code);
                throw new SecurityException();
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageHandlerContext context) {
        return true;
    }
}
