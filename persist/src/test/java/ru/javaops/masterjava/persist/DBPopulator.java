package ru.javaops.masterjava.persist;

import com.google.common.io.Resources;
import org.skife.jdbi.v2.Handle;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Restrictor on 19.11.2016.
 */
public class DBPopulator {

    public static void executeSqlScript(String scriptLocation) {
        String script = loadResource(scriptLocation);
        try (Handle h = DBIProvider.getDBI().open()) {
            h.createScript(script).execute();
        }
    }

    private static String loadResource(String resourceName) {
        String result = "";
        try (Reader reader = new InputStreamReader(Resources.getResource(resourceName).openStream())) {
            while (reader.ready()) {
                result += (char)reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
