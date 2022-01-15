package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * UserBilling entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "UserBilling entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "user_billing")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserBilling extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_billing_name")
    private String userBillingName;

    @Column(name = "user_billing_street_1")
    private String userBillingStreet1;

    @Column(name = "user_billing_street_2")
    private String userBillingStreet2;

    @Column(name = "user_billing_city")
    private String userBillingCity;

    @Column(name = "user_billing_state")
    private String userBillingState;

    @Column(name = "user_billing_country")
    private String userBillingCountry;

    @Column(name = "user_billing_zipcode")
    private String userBillingZipcode;

    @JsonIgnoreProperties(value = {"userBilling", "user"}, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private UserPayment userPayment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserBilling id(Long id) {
        this.id = id;
        return this;
    }

    public String getUserBillingName() {
        return this.userBillingName;
    }

    public UserBilling userBillingName(String userBillingName) {
        this.userBillingName = userBillingName;
        return this;
    }

    public void setUserBillingName(String userBillingName) {
        this.userBillingName = userBillingName;
    }

    public String getUserBillingStreet1() {
        return this.userBillingStreet1;
    }

    public UserBilling userBillingStreet1(String userBillingStreet1) {
        this.userBillingStreet1 = userBillingStreet1;
        return this;
    }

    public void setUserBillingStreet1(String userBillingStreet1) {
        this.userBillingStreet1 = userBillingStreet1;
    }

    public String getUserBillingStreet2() {
        return this.userBillingStreet2;
    }

    public UserBilling userBillingStreet2(String userBillingStreet2) {
        this.userBillingStreet2 = userBillingStreet2;
        return this;
    }

    public void setUserBillingStreet2(String userBillingStreet2) {
        this.userBillingStreet2 = userBillingStreet2;
    }

    public String getUserBillingCity() {
        return this.userBillingCity;
    }

    public UserBilling userBillingCity(String userBillingCity) {
        this.userBillingCity = userBillingCity;
        return this;
    }

    public void setUserBillingCity(String userBillingCity) {
        this.userBillingCity = userBillingCity;
    }

    public String getUserBillingState() {
        return this.userBillingState;
    }

    public UserBilling userBillingState(String userBillingState) {
        this.userBillingState = userBillingState;
        return this;
    }

    public void setUserBillingState(String userBillingState) {
        this.userBillingState = userBillingState;
    }

    public String getUserBillingCountry() {
        return this.userBillingCountry;
    }

    public UserBilling userBillingCountry(String userBillingCountry) {
        this.userBillingCountry = userBillingCountry;
        return this;
    }

    public void setUserBillingCountry(String userBillingCountry) {
        this.userBillingCountry = userBillingCountry;
    }

    public String getUserBillingZipcode() {
        return this.userBillingZipcode;
    }

    public UserBilling userBillingZipcode(String userBillingZipcode) {
        this.userBillingZipcode = userBillingZipcode;
        return this;
    }

    public void setUserBillingZipcode(String userBillingZipcode) {
        this.userBillingZipcode = userBillingZipcode;
    }

    public UserPayment getUserPayment() {
        return this.userPayment;
    }

    public UserBilling userPayment(UserPayment userPayment) {
        this.setUserPayment(userPayment);
        return this;
    }

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserBilling)) {
            return false;
        }
        return id != null && id.equals(((UserBilling) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserBilling{" +
                "id=" + getId() +
                ", userBillingName='" + getUserBillingName() + "'" +
                ", userBillingStreet1='" + getUserBillingStreet1() + "'" +
                ", userBillingStreet2='" + getUserBillingStreet2() + "'" +
                ", userBillingCity='" + getUserBillingCity() + "'" +
                ", userBillingState='" + getUserBillingState() + "'" +
                ", userBillingCountry='" + getUserBillingCountry() + "'" +
                ", userBillingZipcode='" + getUserBillingZipcode() + "'" +
                "}";
    }
}
