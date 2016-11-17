package ru.javaops.masterjava.export;

import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.GroupDao;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Restrictor on 18.11.2016.
 */
public class GroupExport {

    private final static GroupDao groupDao = DBIProvider.getDao(GroupDao.class);

    public static Map<String, Group> process(final InputStream is) throws XMLStreamException {
        final StaxStreamProcessor processor = new StaxStreamProcessor(is);
        Map<String, Group> groups = new HashMap<>();

        while (processor.doUntil(XMLEvent.START_ELEMENT, "Project")) {
            final String projectName = processor.getAttribute("name");

            while (processor.doUntil(XMLEvent.START_ELEMENT, "Group")) {
                final String groupName = processor.getAttribute("name");
                //TODO
                final String groupStatus = processor.getAttribute("type");

                final Group group = groupDao.save(new Group(groupName, projectName));
                groups.put(groupName, group);
            }
        }
        return groups;
    }

}
