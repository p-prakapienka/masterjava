package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.io.File;
import javax.activation.DataHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Restrictor on 24.05.2017.
 */
@Slf4j
public class MailWSClientMain {
    public static final String FILE_NAME = "config_templates/version.html";

    public static void main(String[] args) {
        ImmutableSet<Addressee> addressees = ImmutableSet.of(
                new Addressee("ancrom@tut.by"));
//                new Addressee("Мастер Java <masterjava@javaops.ru>"),
//                new Addressee("Bad Email <bad_email.ru>"));

        try {
            String state = MailWSClient.sendBulkMail(addressees, ImmutableSet.of(), "Subject", "Body",
                ImmutableList.of(
                    new Attach("version.html", new DataHandler(new File(FILE_NAME).toURI().toURL()))
                ));
            System.out.println(state);
        } catch (Throwable e) {
            log.error(e.toString(), e);
        }
    }
}
