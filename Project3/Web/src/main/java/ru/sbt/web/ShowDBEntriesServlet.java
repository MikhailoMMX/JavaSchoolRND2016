package ru.sbt.web;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.springframework.web.servlet.ModelAndView;
import ru.sbt.data.Account;
import ru.sbt.data.Client;
import ru.sbt.data.Document;
import ru.sbt.hibernateutil.HibernateUtil;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public class ShowDBEntriesServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletOutputStream outputStream = servletResponse.getOutputStream();
        Transaction tx = null;
        try(Session session = HibernateUtil.openSession();) {
            tx = session.getTransaction();
            tx.begin();
            List<Client> clients = session.createQuery("FROM Client").list();
            List<Account> accounts = session.createQuery("FROM Account").list();
            List<Document> documents = session.createQuery("FROM Document").list();
            tx.commit();
            outputStream.println("Clients:");
            for (Client client : clients)
                outputStream.println(client.getName());
            outputStream.println();

            outputStream.println("Accounts:");
            for (Account acc: accounts)
                outputStream.println(
                        "Number: " + acc.getAccNum() +
                        ", Saldo: " + acc.getSaldo() +
                        ", Client: " + acc.getClient().getName()
                );
            outputStream.println();

            outputStream.println("Documents:");
            for (Document doc: documents)
                outputStream.println("" +
                        "id=" + doc.getId() +
                        ", debit="+ doc.getAccDT().getId()+"("+ doc.getAccDT().getClient().getName()+")" +
                        ", credit="+ doc.getAccCT().getId()+"("+ doc.getAccCT().getClient().getName()+")" +
                        ", sum=" + doc.getSumma()+
                        ", purpose=" + doc.getPurpose()+
                        ", date=" + doc.getDocDate()
                );
        }
        catch (Exception e){
            outputStream.println(e.getMessage());
            //if (tx!=null)
                //tx.rollback();
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
