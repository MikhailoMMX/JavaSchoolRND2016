package ru.sbt.data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
  * Хранит информацию о документе
 */
@Entity
@Table(name = "DOCUMENTS")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ACC_DT_ID", referencedColumnName = "ID")
    private Account accDT;

    @ManyToOne
    @JoinColumn(name = "ACC_CT_ID", referencedColumnName = "ID")
    private Account accCT;

    private BigDecimal summa;

    private String purpose;

    @Temporal(TemporalType.DATE)
    @Column(name = "DOC_DATE")
    private Date docDate;

    /**
     * Конструктор по-умолчанию
     */
    public Document() {
    }

    /**
     * Конструктор для документа
     * @param id         идентификатор документа
     * @param accDT      идентификатор счета дебета
     * @param accCT      идентификатор счета кредита
     * @param summa      сумма
     * @param purpose    назначение документа
     * @param docDate    дата и время документа
     */
    public Document(long id, Account accDT, Account accCT, BigDecimal summa, String purpose, Date docDate) {
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
    public Document(long id, Account accDT, Account accCT, BigDecimal summa, String purpose) {
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
    public Account getAccDT() {
        return accDT;
    }

    /**
     * Возвращает идентификатор счета кредита
     * @return идентификатор счета кредита
     */
    public Account getAccCT() {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setAccDT(Account accDT) {
        this.accDT = accDT;
    }

    public void setAccCT(Account accCT) {
        this.accCT = accCT;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }
}
