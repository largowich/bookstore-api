package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.ShippingAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:06
 * <p>
 * Service Interface for managing {@link ShippingAddress}.
 */
public interface ShippingAddressService {
    /**
     * Save a shippingAddress.
     *
     * @param shippingAddress the entity to save.
     * @return the persisted entity.
     */
    ShippingAddress save(ShippingAddress shippingAddress);

    /**
     * Partially updates a shippingAddress.
     *
     * @param shippingAddress the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ShippingAddress> partialUpdate(ShippingAddress shippingAddress);

    /**
     * Get all the shippingAddresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShippingAddress> findAll(Pageable pageable);

    /**
     * Get the "id" shippingAddress.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShippingAddress> findOne(Long id);

    /**
     * Delete the "id" shippingAddress.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
