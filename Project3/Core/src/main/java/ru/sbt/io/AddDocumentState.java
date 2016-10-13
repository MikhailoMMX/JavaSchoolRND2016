package ru.sbt.io;

import org.springframework.transaction.annotation.Transactional;
import ru.sbt.data.Account;
import ru.sbt.data.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Состояние обрабатывает команду добавления документа
 */
@Transactional
class AddDocumentState implements State {
    @Override
    public void readCommand(Context context) {
        System.out.print("Enter debit account number: ");
        String accDTNum = context.getScanner().nextLine();
        Account accDT = context.getAccountRepository().findByAccNum(accDTNum);
        if (accDT == null){
            System.out.println("No such account");
            return;
        }
        System.out.print("Enter credit account number: ");
        String accCTNum = context.getScanner().nextLine();
        Account accCT = context.getAccountRepository().findByAccNum(accCTNum);
        if (accCT == null){
            System.out.println("No such account");
            return;
        }
        System.out.print("Enter summa: ");
        BigDecimal summa = context.getScanner().nextBigDecimal();
        context.getScanner().nextLine();
        if (accCT.getSaldo().compareTo(summa)<0){
            System.out.println("Not enough saldo on credit account");
            return;
        }
        System.out.print("Enter purpose: ");
        String purpose = context.getScanner().nextLine();
        Document doc = new Document();
        doc.setAccCT(accCT);
        doc.setAccDT(accDT);
        doc.setDocDate(new Date());
        doc.setPurpose(purpose);
        doc.setSumma(summa);
        context.getDocumentRepository().save(doc);
        accCT.setSaldo(accCT.getSaldo().subtract(summa));
        accDT.setSaldo(accDT.getSaldo().add(summa));
        context.getAccountRepository().save(accDT);
        context.getAccountRepository().save(accCT);
    }

    @Override
    public String stateName() {
        return "add document";
    }
}
