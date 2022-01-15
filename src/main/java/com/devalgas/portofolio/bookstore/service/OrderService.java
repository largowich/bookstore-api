package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:05
 * <p>
 * Service Interface for managing {@link Order}.
 */
public interface OrderService {
    /**
     * Save a order.
     *
     * @param order the entity to save.
     * @return the persisted entity.
     */
    Order save(Order order);

    /**
     * Partially updates a order.
     *
     * @param order the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Order> partialUpdate(Order order);

    /**
     * Get all the orders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Order> findAll(Pageable pageable);

    /**
     * Get the "id" order.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Order> findOne(Long id);

    /**
     * Delete the "id" order.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * create a order.
     *
     * @param shoppingCart    the shoppingCart of the entity.
     * @param shippingAddress the shippingAddress of the entity.
     * @param billingAddress  the billingAddress of the entity.
     * @param shippingMethod  the shippingMethod of the entity.
     * @param user            the user of the entity.
     * @return the persisted entity.
     */
    Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, String shippingMethod, User user
    );
}
