package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * UserPayment entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "UserPayment entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "user_payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserPayment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "card_name")
    private String cardName;

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

    @JsonIgnoreProperties(value = {"userPayment"}, allowSetters = true)
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userPayment")
    @JoinColumn(unique = true)
    private UserBilling userBilling;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPayment id(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public UserPayment type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardName() {
        return this.cardName;
    }

    public UserPayment cardName(String cardName) {
        this.cardName = cardName;
        return this;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public UserPayment cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getExpiryMonth() {
        return this.expiryMonth;
    }

    public UserPayment expiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
        return this;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return this.expiryYear;
    }

    public UserPayment expiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
        return this;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public Integer getCvc() {
        return this.cvc;
    }

    public UserPayment cvc(Integer cvc) {
        this.cvc = cvc;
        return this;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public String getHolderName() {
        return this.holderName;
    }

    public UserPayment holderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Boolean getDefaultPayment() {
        return this.defaultPayment;
    }

    public UserPayment defaultPayment(Boolean defaultPayment) {
        this.defaultPayment = defaultPayment;
        return this;
    }

    public void setDefaultPayment(Boolean defaultPayment) {
        this.defaultPayment = defaultPayment;
    }

    public UserBilling getUserBilling() {
        return this.userBilling;
    }

    public UserPayment userBilling(UserBilling userBilling) {
        this.setUserBilling(userBilling);
        return this;
    }

    public void setUserBilling(UserBilling userBilling) {
        this.userBilling = userBilling;
    }

    public User getUser() {
        return this.user;
    }

    public UserPayment user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPayment)) {
            return false;
        }
        return id != null && id.equals(((UserPayment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPayment{" +
                "id=" + getId() +
                ", type='" + getType() + "'" +
                ", cardName='" + getCardName() + "'" +
                ", cardNumber='" + getCardNumber() + "'" +
                ", expiryMonth=" + getExpiryMonth() +
                ", expiryYear=" + getExpiryYear() +
                ", cvc=" + getCvc() +
                ", holderName='" + getHolderName() + "'" +
                ", defaultPayment='" + getDefaultPayment() + "'" +
                "}";
    }
}
