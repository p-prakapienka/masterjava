package ru.javaops.masterjava.xml;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Restrictor on 11.10.2016.
 * Xml lesson main class.
 */
public class XMLMain {
    public static void main(String[] args) throws Exception {
        String project = "topjava";
        String xmlPath = "payload.xml";

        List<UserInfo> users = readProjectUsersStax(project, xmlPath);
        writeHtmlFileStax(project, users);
    }

    private static List<UserInfo> readProjectUsersStax(String project, String path) throws Exception {
        List<UserInfo> users = new ArrayList<>();

        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource(path).openStream())) {
            XMLStreamReader reader = processor.getReader();
            String user = null;
            List<String> groups = new ArrayList<>();
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLEvent.START_ELEMENT) {
                    if ("fullName".equals(reader.getLocalName())) {
                        user = reader.getElementText();
                    }
                    if (user != null) {
                        if ("Group".equals(reader.getLocalName())
                                && !project.equals(reader.getAttributeValue(0))) {
                            user = null;
                        } else if ("groupName".equals(reader.getLocalName())) {
                            groups.add(reader.getElementText());
                        }
                    }
                } else if (event == XMLEvent.END_ELEMENT) {
                    if ("User".equals(reader.getLocalName())) {
                        if (groups.size() > 0) {
                            final String userName = user;
                            groups.forEach(g -> users.add(new UserInfo(userName, g)));
                        }
                        groups = new ArrayList<>();
                        user = null;
                    }
                }
            }
        }
        return users;
    }

    private static void writeHtmlFileStax(String project, List<UserInfo> users) throws Exception {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("result.html"));
        writer.writeStartElement("html");
        writer.writeStartElement("body");
        writer.writeStartElement("h1");
        writer.writeCharacters(project);
        writer.writeEndElement();
        writer.writeStartElement("table");
        for (UserInfo ui : users) {
            writer.writeStartElement("tr");
            writer.writeStartElement("td");
            writer.writeCharacters(ui.userName);
            writer.writeEndElement();
            writer.writeStartElement("td");
            writer.writeCharacters(ui.group);
            writer.writeEndElement();
            writer.writeEndElement();
        }
        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndElement();
    }

    private static class UserInfo {
        String userName;
        String group;

        UserInfo(String userName, String group) {
            this.userName = userName;
            this.group = group;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "userName='" + userName + '\'' +
                    ", group='" + group + '\'' +
                    '}';
        }
    }
}
