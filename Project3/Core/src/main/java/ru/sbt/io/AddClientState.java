package ru.sbt.io;

import org.springframework.transaction.annotation.Transactional;
import ru.sbt.data.Client;


/**
 * Состояние обрабатывает команду добавления клиента
 */
class AddClientState implements State {
    @Override
    @Transactional
    public void readCommand(Context context) {
        System.out.print("Enter name: ");
        String name = context.getScanner().nextLine();
        Client client = new Client();
        client.setName(name);
        context.getClientRepository().save(client);
    }

    @Override
    public String stateName() {
        return "add client";
    }
}
