package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * /**
 * UserShipping entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "UserShipping entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "user_shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserShipping extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_shipping_name")
    private String userShippingName;

    @Column(name = "user_shipping_street_1")
    private String userShippingStreet1;

    @Column(name = "user_shipping_street_2")
    private String userShippingStreet2;

    @Column(name = "user_shipping_city")
    private String userShippingCity;

    @Column(name = "user_shipping_state")
    private String userShippingState;

    @Column(name = "user_shipping_country")
    private String userShippingCountry;

    @Column(name = "user_shipping_zipcode")
    private String userShippingZipcode;

    @Column(name = "user_shipping_default")
    private Boolean userShippingDefault;

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

    public UserShipping id(Long id) {
        this.id = id;
        return this;
    }

    public String getUserShippingName() {
        return this.userShippingName;
    }

    public UserShipping userShippingName(String userShippingName) {
        this.userShippingName = userShippingName;
        return this;
    }

    public void setUserShippingName(String userShippingName) {
        this.userShippingName = userShippingName;
    }

    public String getUserShippingStreet1() {
        return this.userShippingStreet1;
    }

    public UserShipping userShippingStreet1(String userShippingStreet1) {
        this.userShippingStreet1 = userShippingStreet1;
        return this;
    }

    public void setUserShippingStreet1(String userShippingStreet1) {
        this.userShippingStreet1 = userShippingStreet1;
    }

    public String getUserShippingStreet2() {
        return this.userShippingStreet2;
    }

    public UserShipping userShippingStreet2(String userShippingStreet2) {
        this.userShippingStreet2 = userShippingStreet2;
        return this;
    }

    public void setUserShippingStreet2(String userShippingStreet2) {
        this.userShippingStreet2 = userShippingStreet2;
    }

    public String getUserShippingCity() {
        return this.userShippingCity;
    }

    public UserShipping userShippingCity(String userShippingCity) {
        this.userShippingCity = userShippingCity;
        return this;
    }

    public void setUserShippingCity(String userShippingCity) {
        this.userShippingCity = userShippingCity;
    }

    public String getUserShippingState() {
        return this.userShippingState;
    }

    public UserShipping userShippingState(String userShippingState) {
        this.userShippingState = userShippingState;
        return this;
    }

    public void setUserShippingState(String userShippingState) {
        this.userShippingState = userShippingState;
    }

    public String getUserShippingCountry() {
        return this.userShippingCountry;
    }

    public UserShipping userShippingCountry(String userShippingCountry) {
        this.userShippingCountry = userShippingCountry;
        return this;
    }

    public void setUserShippingCountry(String userShippingCountry) {
        this.userShippingCountry = userShippingCountry;
    }

    public String getUserShippingZipcode() {
        return this.userShippingZipcode;
    }

    public UserShipping userShippingZipcode(String userShippingZipcode) {
        this.userShippingZipcode = userShippingZipcode;
        return this;
    }

    public void setUserShippingZipcode(String userShippingZipcode) {
        this.userShippingZipcode = userShippingZipcode;
    }

    public Boolean getUserShippingDefault() {
        return this.userShippingDefault;
    }

    public UserShipping userShippingDefault(Boolean userShippingDefault) {
        this.userShippingDefault = userShippingDefault;
        return this;
    }

    public void setUserShippingDefault(Boolean userShippingDefault) {
        this.userShippingDefault = userShippingDefault;
    }

    public User getUser() {
        return this.user;
    }

    public UserShipping user(User user) {
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
        if (!(o instanceof UserShipping)) {
            return false;
        }
        return id != null && id.equals(((UserShipping) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserShipping{" +
                "id=" + getId() +
                ", userShippingName='" + getUserShippingName() + "'" +
                ", userShippingStreet1='" + getUserShippingStreet1() + "'" +
                ", userShippingStreet2='" + getUserShippingStreet2() + "'" +
                ", userShippingCity='" + getUserShippingCity() + "'" +
                ", userShippingState='" + getUserShippingState() + "'" +
                ", userShippingCountry='" + getUserShippingCountry() + "'" +
                ", userShippingZipcode='" + getUserShippingZipcode() + "'" +
                ", userShippingDefault='" + getUserShippingDefault() + "'" +
                "}";
    }
}
