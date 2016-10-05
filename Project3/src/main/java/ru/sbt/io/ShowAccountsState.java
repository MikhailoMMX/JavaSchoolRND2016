package ru.sbt.io;

import ru.sbt.data.Account;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Состояние обрабатывает команду отобрашения счетов
 */
class ShowAccountsState implements State {
    @Override
    public void readCommand(Context context) {
        Iterable<Account> all = context.getAccountRepository().findAll();
        System.out.println("Accounts:");
        for (Account account : all){
            System.out.println(
                    "[id=" + account.getId()+
                    ", number=" + account.getAccNum() +
                    ", saldo="+ account.getSaldo() +
                    ", client="+ account.getClient().getName()+
                    "]");
        }
    }

    @Override
    public String stateName() {
        return "show accounts";
    }
}
