package ru.javaops.web;

import com.typesafe.config.Config;
import java.util.List;
import javax.xml.ws.Binding;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.handler.Handler;
import org.slf4j.event.Level;
import ru.javaops.masterjava.ExceptionType;
import ru.javaops.masterjava.config.Configs;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Map;
import ru.javaops.web.handler.Handlers;
import ru.javaops.web.handler.Handlers.SoapClientLoggingHandler;

public class WsClient<T> {
    private static Config HOSTS;

    private final Class<T> serviceClass;
    private final Service service;
    private HostConfig hostConfig;

    public static class HostConfig {
        public final String endpoint;
        public final Level clientDebugLevel;
        public final Level serverDebugLevel;
        public final String user;
        public final String password;
        public final String authHeader;
        public final Handlers.SoapClientLoggingHandler clientLoggingHandler;

        public HostConfig(Config config, String endpointAddress) {
            endpoint = config.getString("endpoint") + endpointAddress;
            clientDebugLevel = config.getEnum(Level.class, "client.debugLevel");
            serverDebugLevel = config.getEnum(Level.class, "server.debugLevel");
            user = config.getString("user");
            password = config.getString("password");
            authHeader = (user != null && password != null) ? AuthUtil.encodeBasicAuthHeader(user, password) : null;
            clientLoggingHandler = clientDebugLevel != null ? new Handlers.SoapClientLoggingHandler(clientDebugLevel) : null;
        }

        public boolean hasAuthorization() {
            return authHeader != null;
        }

        public boolean hasHandler() {
            return clientLoggingHandler != null;
        }
    }

    static {
        HOSTS = Configs.getConfig("hosts.conf", "hosts");
    }

    public WsClient(URL wsdlUrl, QName qname, Class<T> serviceClass) {
        this.serviceClass = serviceClass;
        this.service = Service.create(wsdlUrl, qname);
    }

    public void init(String host, String endpointAddress) {
        this.hostConfig = new HostConfig(
            HOSTS.getConfig(host).withFallback(Configs.getConfig("defaults.conf")), endpointAddress);
    }

    public HostConfig getHostConfig() {
        return hostConfig;
    }

    //  Post is not thread-safe (http://stackoverflow.com/a/10601916/548473)
    public T getPort(WebServiceFeature... features) {
        T port = service.getPort(serviceClass, features);
        BindingProvider bp = (BindingProvider)port;
        Map<String, Object> requestContext = bp.getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, hostConfig.endpoint);
        if (hostConfig.hasAuthorization()) {
            setAuth(port, hostConfig.user, hostConfig.password);
        }
        if (hostConfig.hasHandler()) {
            setHandler(port, hostConfig.clientLoggingHandler);
        }
        return port;
    }

    public static <T> void setHandler(T port, Handler handler) {
        Binding binding = ((BindingProvider)port).getBinding();
        List<Handler> handlerList = binding.getHandlerChain();
        handlerList.add(handler);
        binding.setHandlerChain(handlerList);
    }

    public static <T> void setAuth(T port, String user, String password) {
        Map<String, Object> requestContext = ((BindingProvider)port).getRequestContext();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, user);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
    }

    public static WebStateException getWebStateException(Exception e) {
        return (e instanceof WebStateException) ?
                (WebStateException) e : new WebStateException(ExceptionType.NETWORK, e);
    }
}
