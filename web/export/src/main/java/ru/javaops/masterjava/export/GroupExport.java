package ru.javaops.masterjava.export;

import lombok.extern.slf4j.Slf4j;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.GroupDao;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.GroupFlag;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Restrictor on 18.11.2016.
 */
@Slf4j
public class GroupExport {

    private final GroupDao groupDao = DBIProvider.getDao(GroupDao.class);

    public Map<String, Group> process(final StaxStreamProcessor processor) throws XMLStreamException {
        log.info("Start processing projects");

        Map<String, Group> groups = new HashMap<>();
        String element;
        String projectName = "";
        while (!(element = processor.doUntilAny(XMLEvent.START_ELEMENT, "Project", "Group", "Cities")).equals("Cities")) {
            if ("Project".equals(element)) {
                projectName = processor.getAttribute("name");
            } else if ("Group".equals(element)) {
                final String groupName = processor.getAttribute("name");
                final GroupFlag groupFlag = GroupFlag.valueOf(processor.getAttribute("type"));

                final Group group = groupDao.save(new Group(groupName, projectName, groupFlag));
                groups.put(groupName, group);
            }
        }
        return groups;
    }

}
