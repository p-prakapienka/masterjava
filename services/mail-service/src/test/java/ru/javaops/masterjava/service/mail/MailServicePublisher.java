package ru.javaops.masterjava.service.mail;

import ru.javaops.masterjava.persist.DBITestProvider;

import javax.xml.ws.Endpoint;

/**
 * Created by Restrictor on 17.05.2017.
 */
public class MailServicePublisher {

    public static void main(String[] args) {
        DBITestProvider.initDBI();
        Endpoint.publish("http://localhost:8080/mail/mailService", new MailServiceImpl());
    }
}
