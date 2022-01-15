package com.devalgas.portofolio.bookstore.domain;

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
 * CartItem entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "CartItem entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "cart_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CartItem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "subtotal", precision = 21, scale = 2)
    private BigDecimal subtotal;

    @OneToOne
    @JoinColumn(unique = true)
    private Book book;

    @OneToMany(mappedBy = "cartItem")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"book", "cartItem"}, allowSetters = true)
    private Set<BookToCartItem> bookToCartItems = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = {"shippingAddress", "billingAddress", "payment", "cartItems", "user"}, allowSetters = true)
    private Order order;

    @ManyToOne
    @JsonIgnoreProperties(value = {"user", "cartItems"}, allowSetters = true)
    private ShoppingCart shoppingCart;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartItem id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getQty() {
        return this.qty;
    }

    public CartItem qty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getSubtotal() {
        return this.subtotal;
    }

    public CartItem subtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Book getBook() {
        return this.book;
    }

    public CartItem book(Book book) {
        this.setBook(book);
        return this;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<BookToCartItem> getBookToCartItems() {
        return this.bookToCartItems;
    }

    public CartItem bookToCartItems(Set<BookToCartItem> bookToCartItems) {
        this.setBookToCartItems(bookToCartItems);
        return this;
    }

    public CartItem addBookToCartItem(BookToCartItem bookToCartItem) {
        this.bookToCartItems.add(bookToCartItem);
        bookToCartItem.setCartItem(this);
        return this;
    }

    public CartItem removeBookToCartItem(BookToCartItem bookToCartItem) {
        this.bookToCartItems.remove(bookToCartItem);
        bookToCartItem.setCartItem(null);
        return this;
    }

    public void setBookToCartItems(Set<BookToCartItem> bookToCartItems) {
        if (this.bookToCartItems != null) {
            this.bookToCartItems.forEach(i -> i.setCartItem(null));
        }
        if (bookToCartItems != null) {
            bookToCartItems.forEach(i -> i.setCartItem(this));
        }
        this.bookToCartItems = bookToCartItems;
    }

    public Order getOrder() {
        return this.order;
    }

    public CartItem order(Order order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public CartItem shoppingCart(ShoppingCart shoppingCart) {
        this.setShoppingCart(shoppingCart);
        return this;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItem)) {
            return false;
        }
        return id != null && id.equals(((CartItem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + getId() +
                ", qty=" + getQty() +
                ", subtotal=" + getSubtotal() +
                "}";
    }
}
