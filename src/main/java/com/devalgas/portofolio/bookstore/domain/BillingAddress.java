package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * BillingAddress entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "BillingAddress entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "billing_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillingAddress extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "billing_address_name")
    private String billingAddressName;

    @Column(name = "billing_address_street_1")
    private String billingAddressStreet1;

    @Column(name = "billing_address_street_2")
    private String billingAddressStreet2;

    @Column(name = "billing_address_city")
    private String billingAddressCity;

    @Column(name = "billing_address_state")
    private String billingAddressState;

    @Column(name = "billing_address_country")
    private String billingAddressCountry;

    @Column(name = "billing_address_zipcode")
    private String billingAddressZipcode;

    @JsonIgnoreProperties(value = {"shippingAddress", "billingAddress", "payment", "user", "cartItemList"}, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Order order;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BillingAddress id(Long id) {
        this.id = id;
        return this;
    }

    public String getBillingAddressName() {
        return this.billingAddressName;
    }

    public BillingAddress billingAddressName(String billingAddressName) {
        this.billingAddressName = billingAddressName;
        return this;
    }

    public void setBillingAddressName(String billingAddressName) {
        this.billingAddressName = billingAddressName;
    }

    public String getBillingAddressStreet1() {
        return this.billingAddressStreet1;
    }

    public BillingAddress billingAddressStreet1(String billingAddressStreet1) {
        this.billingAddressStreet1 = billingAddressStreet1;
        return this;
    }

    public void setBillingAddressStreet1(String billingAddressStreet1) {
        this.billingAddressStreet1 = billingAddressStreet1;
    }

    public String getBillingAddressStreet2() {
        return this.billingAddressStreet2;
    }

    public BillingAddress billingAddressStreet2(String billingAddressStreet2) {
        this.billingAddressStreet2 = billingAddressStreet2;
        return this;
    }

    public void setBillingAddressStreet2(String billingAddressStreet2) {
        this.billingAddressStreet2 = billingAddressStreet2;
    }

    public String getBillingAddressCity() {
        return this.billingAddressCity;
    }

    public BillingAddress billingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
        return this;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return this.billingAddressState;
    }

    public BillingAddress billingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
        return this;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressCountry() {
        return this.billingAddressCountry;
    }

    public BillingAddress billingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
        return this;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }

    public String getBillingAddressZipcode() {
        return this.billingAddressZipcode;
    }

    public BillingAddress billingAddressZipcode(String billingAddressZipcode) {
        this.billingAddressZipcode = billingAddressZipcode;
        return this;
    }

    public void setBillingAddressZipcode(String billingAddressZipcode) {
        this.billingAddressZipcode = billingAddressZipcode;
    }

    public Order getOrder() {
        return this.order;
    }

    public BillingAddress order(Order order) {
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
        if (!(o instanceof BillingAddress)) {
            return false;
        }
        return id != null && id.equals(((BillingAddress) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillingAddress{" +
                "id=" + getId() +
                ", billingAddressName='" + getBillingAddressName() + "'" +
                ", billingAddressStreet1='" + getBillingAddressStreet1() + "'" +
                ", billingAddressStreet2='" + getBillingAddressStreet2() + "'" +
                ", billingAddressCity='" + getBillingAddressCity() + "'" +
                ", billingAddressState='" + getBillingAddressState() + "'" +
                ", billingAddressCountry='" + getBillingAddressCountry() + "'" +
                ", billingAddressZipcode='" + getBillingAddressZipcode() + "'" +
                "}";
    }
}
