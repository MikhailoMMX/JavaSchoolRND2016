package ru.sbt.data;

/**
  * Хранит информацию о клиенте
 */
public class Client {
    private final long id;
    private final String name;

    /**
     *
     * @param id      идентификатор клиента
     * @param name    имя клиента
     */
    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает   идентификатор клиента
     * @return      идентификатор клиента
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает   имя клиента
     * @return      имя клиента
     */
    public String getName() {
        return name;
    }
}
