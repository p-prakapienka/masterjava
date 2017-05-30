package ru.javaops.web;

import com.typesafe.config.Config;
import ru.javaops.masterjava.ExceptionType;
import ru.javaops.masterjava.config.Configs;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Map;

public class WsClient<T> {
    private static Config HOSTS;

    private final Class<T> serviceClass;
    private final Service service;

    private T port;

    static {
        HOSTS = Configs.getConfig("hosts.conf", "hosts");
    }

    public WsClient(URL wsdlUrl, QName qname, Class<T> serviceClass) {
        this.serviceClass = serviceClass;
        this.service = Service.create(wsdlUrl, qname);
    }

    public void init(String host, String endpointAddress) {
        port = service.getPort(serviceClass);
        BindingProvider bp = (BindingProvider)port;
        Map<String, Object> requestContext = bp.getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, HOSTS.getString(host) + endpointAddress);
    }

    public T getPort() {
        return port;
    }

    public static WebStateException getWebStateException(Exception e) {
        return (e instanceof WebStateException) ?
                (WebStateException) e : new WebStateException(ExceptionType.NETWORK, e);
    }
}
