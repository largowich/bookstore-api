package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ShippingAddress entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "ShippingAddress entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "shipping_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShippingAddress extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipping_address_name")
    private String shippingAddressName;

    @Column(name = "shipping_address_street_1")
    private String shippingAddressStreet1;

    @Column(name = "shipping_address_street_2")
    private String shippingAddressStreet2;

    @Column(name = "shipping_address_city")
    private String shippingAddressCity;

    @Column(name = "shipping_address_state")
    private String shippingAddressState;

    @Column(name = "shipping_address_country")
    private String shippingAddressCountry;

    @Column(name = "shipping_address_zipcode")
    private String shippingAddressZipcode;

    @JsonIgnoreProperties(value = {"shippingAddress", "billingAddress", "payment", "cartItems", "user"}, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Order order;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingAddress id(Long id) {
        this.id = id;
        return this;
    }

    public String getShippingAddressName() {
        return this.shippingAddressName;
    }

    public ShippingAddress shippingAddressName(String shippingAddressName) {
        this.shippingAddressName = shippingAddressName;
        return this;
    }

    public void setShippingAddressName(String shippingAddressName) {
        this.shippingAddressName = shippingAddressName;
    }

    public String getShippingAddressStreet1() {
        return this.shippingAddressStreet1;
    }

    public ShippingAddress shippingAddressStreet1(String shippingAddressStreet1) {
        this.shippingAddressStreet1 = shippingAddressStreet1;
        return this;
    }

    public void setShippingAddressStreet1(String shippingAddressStreet1) {
        this.shippingAddressStreet1 = shippingAddressStreet1;
    }

    public String getShippingAddressStreet2() {
        return this.shippingAddressStreet2;
    }

    public ShippingAddress shippingAddressStreet2(String shippingAddressStreet2) {
        this.shippingAddressStreet2 = shippingAddressStreet2;
        return this;
    }

    public void setShippingAddressStreet2(String shippingAddressStreet2) {
        this.shippingAddressStreet2 = shippingAddressStreet2;
    }

    public String getShippingAddressCity() {
        return this.shippingAddressCity;
    }

    public ShippingAddress shippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
        return this;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressState() {
        return this.shippingAddressState;
    }

    public ShippingAddress shippingAddressState(String shippingAddressState) {
        this.shippingAddressState = shippingAddressState;
        return this;
    }

    public void setShippingAddressState(String shippingAddressState) {
        this.shippingAddressState = shippingAddressState;
    }

    public String getShippingAddressCountry() {
        return this.shippingAddressCountry;
    }

    public ShippingAddress shippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
        return this;
    }

    public void setShippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
    }

    public String getShippingAddressZipcode() {
        return this.shippingAddressZipcode;
    }

    public ShippingAddress shippingAddressZipcode(String shippingAddressZipcode) {
        this.shippingAddressZipcode = shippingAddressZipcode;
        return this;
    }

    public void setShippingAddressZipcode(String shippingAddressZipcode) {
        this.shippingAddressZipcode = shippingAddressZipcode;
    }

    public Order getOrder() {
        return this.order;
    }

    public ShippingAddress order(Order order) {
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
        if (!(o instanceof ShippingAddress)) {
            return false;
        }
        return id != null && id.equals(((ShippingAddress) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + getId() +
                ", shippingAddressName='" + getShippingAddressName() + "'" +
                ", shippingAddressStreet1='" + getShippingAddressStreet1() + "'" +
                ", shippingAddressStreet2='" + getShippingAddressStreet2() + "'" +
                ", shippingAddressCity='" + getShippingAddressCity() + "'" +
                ", shippingAddressState='" + getShippingAddressState() + "'" +
                ", shippingAddressCountry='" + getShippingAddressCountry() + "'" +
                ", shippingAddressZipcode='" + getShippingAddressZipcode() + "'" +
                "}";
    }
}
