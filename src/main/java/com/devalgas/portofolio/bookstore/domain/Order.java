package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Order entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "Order entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "bst_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_total", precision = 21, scale = 2)
    private BigDecimal orderTotal;

    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate;

    @JsonIgnoreProperties(value = {"order"}, allowSetters = true)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private ShippingAddress shippingAddress;

    @JsonIgnoreProperties(value = {"order"}, allowSetters = true)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private BillingAddress billingAddress;

    @JsonIgnoreProperties(value = {"order"}, allowSetters = true)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Payment payment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"book", "bookToCartItems", "order", "shoppingCart"}, allowSetters = true)
    private Set<CartItem> cartItems = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private User user;


    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order id(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public Order orderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getShippingDate() {
        return this.shippingDate;
    }

    public Order shippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingMethod() {
        return this.shippingMethod;
    }

    public Order shippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public Order orderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderTotal() {
        return this.orderTotal;
    }

    public Order orderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
        return this;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public ShippingAddress getShippingAddress() {
        return this.shippingAddress;
    }

    public Order shippingAddress(ShippingAddress shippingAddress) {
        this.setShippingAddress(shippingAddress);
        return this;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return this.billingAddress;
    }

    public Order billingAddress(BillingAddress billingAddress) {
        this.setBillingAddress(billingAddress);
        return this;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public Order payment(Payment payment) {
        this.setPayment(payment);
        return this;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public Order cartItems(Set<CartItem> cartItems) {
        this.setCartItems(cartItems);
        return this;
    }

    public Order addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setOrder(this);
        return this;
    }

    public Order removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
        cartItem.setOrder(null);
        return this;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        if (this.cartItems != null) {
            this.cartItems.forEach(i -> i.setOrder(null));
        }
        if (cartItems != null) {
            cartItems.forEach(i -> i.setOrder(this));
        }
        this.cartItems = cartItems;
    }

    public User getUser() {
        return this.user;
    }

    public Order user(User user) {
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
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                ", orderDate='" + getOrderDate() + "'" +
                ", shippingDate='" + getShippingDate() + "'" +
                ", shippingMethod='" + getShippingMethod() + "'" +
                ", orderStatus='" + getOrderStatus() + "'" +
                ", estimatedDeliveryDate='" + getEstimatedDeliveryDate() + "'" +
                ", orderTotal=" + getOrderTotal() +
                "}";
    }
}
