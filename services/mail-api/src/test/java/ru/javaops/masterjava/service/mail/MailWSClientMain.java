package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableList;

/**
 * Created by Restrictor on 24.05.2017.
 */
public class MailWSClientMain {
    public static void main(String[] args) {
        MailWSClient.sendMail(
                ImmutableList.of(new Addressee("Григорий Кислин <gkislin@javaops.ru>")),
                ImmutableList.of(new Addressee("Мастер Java <masterjava@javaops.ru>")), "Subject", "Body");
    }
}
