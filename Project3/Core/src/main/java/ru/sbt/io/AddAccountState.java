package ru.sbt.io;

import org.h2.command.dml.Select;
import ru.sbt.data.Account;
import ru.sbt.data.Client;

import java.math.BigDecimal;

/**
 * Состояние обрабатывает команду добавления счета
 */
class AddAccountState implements State {
    @Override
    public void readCommand(Context context) {
        System.out.print("Enter client name: ");
        String name = context.getScanner().nextLine();
        Client client = context.getClientRepository().findByName(name);
        if (client != null){
            boolean accNumNotOk;
            String accNum;
            do {
                System.out.print("Enter account number: ");
                accNum = context.getScanner().nextLine();
                Account foundByAccNum = context.getAccountRepository().findByAccNum(accNum);
                accNumNotOk = foundByAccNum != null;
                if (accNumNotOk)
                    System.out.println("Account number already exists (must be unique)");
            }while (accNumNotOk);

            Account account = new Account();
            account.setClient(client);
            account.setSaldo(new BigDecimal(0));
            account.setAccNum(accNum);
            context.getAccountRepository().save(account);
        }
        else
            System.out.println("No such client");
    }

    @Override
    public String stateName() {
        return "add account";
    }
}
