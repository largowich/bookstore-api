package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:06
 * <p>
 * Service Interface for managing {@link ShoppingCart}.
 */
public interface ShoppingCartService {
    /**
     * Save a shoppingCart.
     *
     * @param shoppingCart the entity to save.
     * @return the persisted entity.
     */
    ShoppingCart save(ShoppingCart shoppingCart);

    /**
     * Partially updates a shoppingCart.
     *
     * @param shoppingCart the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ShoppingCart> partialUpdate(ShoppingCart shoppingCart);

    /**
     * Get all the shoppingCarts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShoppingCart> findAll(Pageable pageable);

    /**
     * Get the "id" shoppingCart.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShoppingCart> findOne(Long id);

    /**
     * Delete the "id" shoppingCart.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Partially updates a shoppingCart.
     *
     * @param shoppingCart the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ShoppingCart> updateShoppingCart(ShoppingCart shoppingCart);

    /**
     * Delete the "shoppingCart" shoppingCart.
     *
     * @param shoppingCart the shoppingCart of the entity.
     */
    void clearShoppingCart(ShoppingCart shoppingCart);
}
