package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.BookToCartItem;
import com.devalgas.portofolio.bookstore.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author devalgas kamga on 13/01/2022. 23:38
 * <p>
 * Spring Data SQL repository for the BookToCartItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookToCartItemRepository extends JpaRepository<BookToCartItem, Long> {
    void deleteByCartItem(CartItem cartItem);
}
