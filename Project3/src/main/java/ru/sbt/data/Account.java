package ru.sbt.data;

import java.math.BigDecimal;

/**
 * Хранит информацию о счете клиента
 */
public class Account {
    private final long id;
    private BigDecimal saldo;
    private final String accNum;
    private final long clientId;

    /**
     * Конструктор для счета
     * @param id          идентификатор счета
     * @param saldo       сальдо
     * @param accNum      номер счета
     * @param clientId    идентификатор клиента
     */
    public Account(long id, BigDecimal saldo, String accNum, long clientId) {
        this.id = id;
        this.saldo = saldo;
        this.accNum = accNum;
        this.clientId = clientId;
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
     * Возвращает идентификатор клиента
     * @return Идентификатор клиента
     */
    public long getClient() {
        return clientId;
    }

    /**
     * Устанавливает новое значение сальдо
     * @param saldo новое значение сальдо
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
