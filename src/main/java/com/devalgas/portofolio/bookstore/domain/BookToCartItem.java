package com.devalgas.portofolio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * BookToCartItem entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "BookToCartItem entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "book_to_cart_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BookToCartItem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    @JsonIgnoreProperties(value = {"book", "bookToCartItems", "order", "shoppingCart"}, allowSetters = true)
    private CartItem cartItem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookToCartItem id(Long id) {
        this.id = id;
        return this;
    }

    public Book getBook() {
        return this.book;
    }

    public BookToCartItem book(Book book) {
        this.setBook(book);
        return this;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CartItem getCartItem() {
        return this.cartItem;
    }

    public BookToCartItem cartItem(CartItem cartItem) {
        this.setCartItem(cartItem);
        return this;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookToCartItem)) {
            return false;
        }
        return id != null && id.equals(((BookToCartItem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookToCartItem{" +
                "id=" + getId() +
                "}";
    }
}
