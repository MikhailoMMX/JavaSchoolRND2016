package ru.sbt.hibernateutil;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Bean;
import ru.sbt.exceptions.AccountNotFoundException;
import ru.sbt.exceptions.ClientNotFoundException;
import ru.sbt.data.Account;
import ru.sbt.data.Client;
import ru.sbt.data.Document;

import java.util.List;

public class DataController implements IDataController{
    @Override
    public Client getClientByName(String name) {
        try(Session session = HibernateUtil.openSession()) {
            Transaction tx = session.getTransaction();
            tx.begin();

            Query queryClient = session.createQuery("FROM Client C WHERE C.name = :Name");
            queryClient.setParameter("Name", name);
            List<Client> clients = queryClient.list();
            if (clients.size() == 0){
                throw new ClientNotFoundException();
            }
            tx.commit();
            return clients.get(0);
        }
    }

    @Override
    public List<Account> getAccounts(Client client) {
        try(Session session = HibernateUtil.openSession()) {
            Transaction tx = session.getTransaction();
            tx.begin();

            Query queryAccount = session.createQuery("FROM Account A WHERE A.client = :client");
            queryAccount.setParameter("client", client);

            tx.commit();
            return queryAccount.list();
        }
    }

    @Override
    public Account getAccountByNumber(String accnum) {
        try(Session session = HibernateUtil.openSession()) {
            Transaction tx = session.getTransaction();
            tx.begin();

            Query queryAcc = session.createQuery("FROM Account A WHERE A.accNum = :accNum");
            queryAcc.setParameter("accNum", accnum);
            List<Account> accResult = queryAcc.list();
            if (accResult.size() == 0){
                throw new AccountNotFoundException();
            }

            tx.commit();
            return accResult.get(0);
        }
    }

    @Override
    public List<Document> getDocumentsForAccount(Account account) {
        try(Session session = HibernateUtil.openSession()) {
            Transaction tx = session.getTransaction();
            tx.begin();

            Query queryAccount = session.createQuery("FROM Document D WHERE D.accDT = :acc OR D.accCT = :acc");
            queryAccount.setParameter("acc", account);
            tx.commit();
            return queryAccount.list();
        }
    }
}
