package ru.javaops.masterjava.export;

import lombok.extern.slf4j.Slf4j;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.CityDao;
import ru.javaops.masterjava.persist.model.City;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Restrictor on 18.11.2016.
 */
@Slf4j
public class CityExport {

    private final CityDao cityDao = DBIProvider.getDao(CityDao.class);

    public Map<String, City> process(final StaxStreamProcessor processor) throws XMLStreamException {
        log.info("Start processing cities");

        Map<String, City> cities = new HashMap<>();
        String element;
        while (!(element = processor.doUntilAny(XMLEvent.START_ELEMENT, "City", "Users")).equals("Users")) {
            if ("City".equals(element)) {
                final String refName = processor.getAttribute("id");
                final String cityName = processor.getReader().getElementText();
                final City city = cityDao.save(new City(cityName));
                cities.put(refName, city);
            }
        }
        return cities;
    }

}
