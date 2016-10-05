package ru.sbt.io;

import ru.sbt.data.Document;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Состояние обрабатывает команду отображения документов
 */
class ShowDocumentsState implements State {
    @Override
    public void readCommand(Context context) {
        Iterable<Document> all = context.getDocumentRepository().findAll();
        System.out.println("Documents:");
        for (Document doc : all){
            System.out.println("[id=" + doc.getId() +
                    ", debit="+ doc.getAccDT().getId()+"("+ doc.getAccDT().getClient().getName()+")" +
                    ", credit="+ doc.getAccCT().getId()+"("+ doc.getAccCT().getClient().getName()+")" +
                    ", sum=" + doc.getSumma()+
                    ", purpose=" + doc.getPurpose()+
                    ", date=" + doc.getDocDate() +
                    "]");
        }
    }

    @Override
    public String stateName() {
        return "show documents";
    }
}
