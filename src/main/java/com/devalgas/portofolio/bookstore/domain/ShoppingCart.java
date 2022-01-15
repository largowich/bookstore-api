package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * ShoppingCart entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "ShoppingCart entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "shopping_cart")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingCart extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grand_total", precision = 21, scale = 2)
    private BigDecimal grandTotal;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"book", "bookToCartItems", "order", "shoppingCart"}, allowSetters = true)
    private Set<CartItem> cartItems = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCart id(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getGrandTotal() {
        return this.grandTotal;
    }

    public ShoppingCart grandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
        return this;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public User getUser() {
        return this.user;
    }

    public ShoppingCart user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public ShoppingCart cartItems(Set<CartItem> cartItems) {
        this.setCartItems(cartItems);
        return this;
    }

    public ShoppingCart addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setShoppingCart(this);
        return this;
    }

    public ShoppingCart removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
        cartItem.setShoppingCart(null);
        return this;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        if (this.cartItems != null) {
            this.cartItems.forEach(i -> i.setShoppingCart(null));
        }
        if (cartItems != null) {
            cartItems.forEach(i -> i.setShoppingCart(this));
        }
        this.cartItems = cartItems;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCart)) {
            return false;
        }
        return id != null && id.equals(((ShoppingCart) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + getId() +
                ", grandTotal=" + getGrandTotal() +
                "}";
    }
}
