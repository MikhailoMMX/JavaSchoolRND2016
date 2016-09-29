package ru.sbt.data;

import java.math.BigDecimal;
import java.util.Date;

/**
  * Хранит информацию о документе
 */
public class Document {
    private final long id;
    private final long accDT;
    private final long accCT;
    private final BigDecimal summa;
    private final String purpose;
    private final Date docDate;

    /**
     * Конструктор для документа
     * @param id         идентификатор документа
     * @param accDT      идентификатор счета дебета
     * @param accCT      идентификатор счета кредита
     * @param summa      сумма
     * @param purpose    назначение документа
     * @param docDate    дата и время документа
     */
    public Document(long id, long accDT, long accCT, BigDecimal summa, String purpose, Date docDate) {
        this.id = id;
        this.accDT = accDT;
        this.accCT = accCT;
        this.summa = summa;
        this.purpose = purpose;
        this.docDate = new Date(docDate.getTime());
    }

    /**
     * Конструктор для документа
     * @param id         идентификатор документа
     * @param accDT      идентификатор счета дебета
     * @param accCT      идентификатор счета кредита
     * @param summa      сумма
     * @param purpose    назначение документа
     */
    public Document(long id, long accDT, long accCT, BigDecimal summa, String purpose) {
        this.id = id;
        this.accDT = accDT;
        this.accCT = accCT;
        this.summa = summa;
        this.purpose = purpose;
        this.docDate = new Date();
    }

    /**
     * Возвращает идентификатор документа
     * @return идентификатор документа
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает идентификатор счета дебета
     * @return идентификатор счета дебета
     */
    public long getAccDT() {
        return accDT;
    }

    /**
     * Возвращает идентификатор счета кредита
     * @return идентификатор счета кредита
     */
    public long getAccCT() {
        return accCT;
    }

    /**
     * Возвращает сумму
     * @return сумма
     */
    public BigDecimal getSumma() {
        return summa;
    }

    /**
     * Возвращает назначение документа
     * @return назначение документа
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Возвращает дату и время документа
     * @return дата и время документа
     */
    public Date getDocDate() {
        return docDate;
    }
}
