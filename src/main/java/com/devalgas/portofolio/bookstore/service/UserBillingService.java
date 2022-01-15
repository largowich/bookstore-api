package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.UserBilling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:07
 * <p>
 * Service Interface for managing {@link UserBilling}.
 */
public interface UserBillingService {
    /**
     * Save a userBilling.
     *
     * @param userBilling the entity to save.
     * @return the persisted entity.
     */
    UserBilling save(UserBilling userBilling);

    /**
     * Partially updates a userBilling.
     *
     * @param userBilling the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserBilling> partialUpdate(UserBilling userBilling);

    /**
     * Get all the userBillings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserBilling> findAll(Pageable pageable);

    /**
     * Get the "id" userBilling.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserBilling> findOne(Long id);

    /**
     * Delete the "id" userBilling.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
