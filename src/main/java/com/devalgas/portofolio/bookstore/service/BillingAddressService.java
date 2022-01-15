package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.BillingAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:02
 * <p>
 * Service Interface for managing {@link BillingAddress}.
 */
public interface BillingAddressService {
    /**
     * Save a billingAddress.
     *
     * @param billingAddress the entity to save.
     * @return the persisted entity.
     */
    BillingAddress save(BillingAddress billingAddress);

    /**
     * Partially updates a billingAddress.
     *
     * @param billingAddress the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BillingAddress> partialUpdate(BillingAddress billingAddress);

    /**
     * Get all the billingAddresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillingAddress> findAll(Pageable pageable);

    /**
     * Get the "id" billingAddress.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillingAddress> findOne(Long id);

    /**
     * Delete the "id" billingAddress.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
