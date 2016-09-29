package com.apress.prospring4.ch6;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class AnnotationJdbcDaoSample{

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-annotation.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean(ContactDao.class);

        List<Contact> contacts = contactDao.findAll();
        for (Contact contact: contacts) {
            System.out.println(contact);
        }

    }


}
