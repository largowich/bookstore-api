package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Payment entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "Payment entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Payment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiry_month")
    private Integer expiryMonth;

    @Column(name = "expiry_year")
    private Integer expiryYear;

    @Column(name = "cvc")
    private Integer cvc;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "default_payment")
    private Boolean defaultPayment;

    @JsonIgnoreProperties(value = { "shippingAddress", "billingAddress", "payment", "cartItems", "user" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Order order;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment id(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Payment type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public Payment cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getExpiryMonth() {
        return this.expiryMonth;
    }

    public Payment expiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
        return this;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return this.expiryYear;
    }

    public Payment expiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
        return this;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public Integer getCvc() {
        return this.cvc;
    }

    public Payment cvc(Integer cvc) {
        this.cvc = cvc;
        return this;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public String getHolderName() {
        return this.holderName;
    }

    public Payment holderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Boolean getDefaultPayment() {
        return this.defaultPayment;
    }

    public Payment defaultPayment(Boolean defaultPayment) {
        this.defaultPayment = defaultPayment;
        return this;
    }

    public void setDefaultPayment(Boolean defaultPayment) {
        this.defaultPayment = defaultPayment;
    }

    public Order getOrder() {
        return this.order;
    }

    public Payment order(Order order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + getId() +
                ", type='" + getType() + "'" +
                ", cardNumber='" + getCardNumber() + "'" +
                ", expiryMonth=" + getExpiryMonth() +
                ", expiryYear=" + getExpiryYear() +
                ", cvc=" + getCvc() +
                ", holderName='" + getHolderName() + "'" +
                ", defaultPayment='" + getDefaultPayment() + "'" +
                "}";
    }
}
