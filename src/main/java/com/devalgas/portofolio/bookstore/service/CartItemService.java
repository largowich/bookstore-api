package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:04
 * Service Interface for managing {@link CartItem}.
 */
public interface CartItemService {
    /**
     * Save a cartItem.
     *
     * @param cartItem the entity to save.
     * @return the persisted entity.
     */
    CartItem save(CartItem cartItem);

    /**
     * Partially updates a cartItem.
     *
     * @param cartItem the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CartItem> partialUpdate(CartItem cartItem);

    /**
     * Get all the cartItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CartItem> findAll(Pageable pageable);

    /**
     * Get the "id" cartItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CartItem> findOne(Long id);

    /**
     * Delete the "id" cartItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Save a cartItem.
     *
     * @param book the id of the entity.
     * @param user the id of the entity.
     * @param qty  the id of the entity.
     * @return the persisted entity.
     */
    CartItem addBookToCartItem(Book book, User user, int qty);

    /**
     * Get all the "shoppingCart" cartItems.
     *
     * @param shoppingCart the shoppingCart of the entity.
     * @return the list of entities.
     */
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    /**
     * Get all the "order" cartItems.
     *
     * @param order the order of the entity.
     * @return the list of entities.
     */
    List<CartItem> findByOrder(Order order);

    /**
     * Partially updates a cartItem.
     *
     * @param cartItem the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CartItem> updateCartItem(CartItem cartItem);

    /**
     * Delete the "cartItem" cartItem.
     *
     * @param cartItem the cartItem of the entity.
     */
    void removeCartItem(CartItem cartItem);

}
