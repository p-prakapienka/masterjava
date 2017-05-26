package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableList;

/**
 * Created by Restrictor on 24.05.2017.
 */
public class MailWSClientMain {
    public static void main(String[] args) {
        MailWSClient.sendMail(
                ImmutableList.of(new Addressee("Pavel Prakapienka <ciulen666@gmail.com>"), new Addressee("Paviel Prakapienka <prakapienka.work@gmail.com>")),
                ImmutableList.of(), "Subject", "Body");
    }
}
