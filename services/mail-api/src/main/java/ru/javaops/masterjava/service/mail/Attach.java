package ru.javaops.masterjava.service.mail;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Attach {
    //http://stackoverflow.com/questions/12250423/jax-ws-datahandler-getname-is-blank-when-called-from-client-side
    protected String name;

    @XmlMimeType("application/octet-stream")
    private DataHandler dataHandler;

}
