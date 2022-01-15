package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.UserPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:07
 * <p>
 * Service Interface for managing {@link UserPayment}.
 */
public interface UserPaymentService {
    /**
     * Save a userPayment.
     *
     * @param userPayment the entity to save.
     * @return the persisted entity.
     */
    UserPayment save(UserPayment userPayment);

    /**
     * Partially updates a userPayment.
     *
     * @param userPayment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserPayment> partialUpdate(UserPayment userPayment);

    /**
     * Get all the userPayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserPayment> findAll(Pageable pageable);

    /**
     * Get the "id" userPayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserPayment> findOne(Long id);

    /**
     * Delete the "id" userPayment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
