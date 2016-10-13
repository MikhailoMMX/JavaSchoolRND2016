package ru.sbt.hibernateutil;

import ru.sbt.data.Account;
import ru.sbt.data.Client;
import ru.sbt.data.Document;

import java.util.List;

public interface IDataController {
    Client getClientByName(String name);
    List<Account> getAccounts(Client client);

    Account getAccountByNumber(String accnum);
    List<Document> getDocumentsForAccount(Account account);
}
