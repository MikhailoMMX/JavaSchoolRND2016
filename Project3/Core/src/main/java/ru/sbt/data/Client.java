package ru.sbt.data;

import javax.persistence.*;
import java.io.Serializable;

/**
  * Хранит информацию о клиенте
 */
@Entity
@Table(name = "CLIENTS")
public class Client implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    private String name;

    /**
     * Конструктор по-умолчанию
     */
    public Client() {
    }

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

    /**
     * Устанавливает идентификатор
     * @param id идентификатор
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Устанавливает имя клиента
     * @param name имя клиента
     */
    public void setName(String name) {
        this.name = name;
    }
}
