package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.CartItem;
import com.devalgas.portofolio.bookstore.domain.Order;
import com.devalgas.portofolio.bookstore.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author devalgas kamga on 13/01/2022. 23:38
 * <p>
 * Spring Data SQL repository for the CartItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<CartItem> findByOrder(Order order);
}
