package ru.javaops.masterjava.export;

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
public class CityExport {

    private final static CityDao cityDao = DBIProvider.getDao(CityDao.class);

    public static Map<String, City> process(final InputStream is) throws XMLStreamException {
        final StaxStreamProcessor processor = new StaxStreamProcessor(is);
        Map<String, City> cities = new HashMap<>();

        while (processor.doUntil(XMLEvent.START_ELEMENT, "City")) {
            final String refName = processor.getAttribute("id");
            final String cityName = processor.getReader().getElementText();
            final City city = cityDao.save(new City(cityName));

            cities.put(refName, city);
        }
        return cities;
    }

}
