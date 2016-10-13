package ru.sbt.data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Хранит информацию о счете клиента
 */
@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    private BigDecimal saldo;

    @Column(name = "ACC_NUM")
    private String accNum;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    private Client client;


    /**
     * Конструктор по-умолчанию
     */
    public Account() {

    }

    /**
     * Конструктор для счета
     * @param id          идентификатор счета
     * @param saldo       сальдо
     * @param accNum      номер счета
     * @param client      клиент
     */
    public Account(long id, BigDecimal saldo, String accNum, Client client) {
        this.id = id;
        this.saldo = saldo;
        this.accNum = accNum;
        this.client = client;
    }

    /**
     * Возвращает идентификатор счета в базе данных
     * @return Идентификатор счета в базе данных
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает сальдо
     * @return Сальдо
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * Возвращает номер счета
     * @return Номер счета
     */
    public String getAccNum() {
        return accNum;
    }

    /**
     * Возвращает клиента
     * @return клиент
     */
    public Client getClient() {
        return client;
    }

    /**
     * Устанавливает новое значение сальдо
     * @param saldo новое значение сальдо
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    /**
     * устанавливает идентификатор
     * @param id идентификатор
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Устанавливает номер счета
     * @param accNum номер счета
     */
    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    /**
     * Устанавливает клиента
     * @param client клиент
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
