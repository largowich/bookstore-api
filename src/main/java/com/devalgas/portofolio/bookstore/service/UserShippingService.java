package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.UserShipping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:08
 * <p>
 * Service Interface for managing {@link UserShipping}.
 */
public interface UserShippingService {
    /**
     * Save a userShipping.
     *
     * @param userShipping the entity to save.
     * @return the persisted entity.
     */
    UserShipping save(UserShipping userShipping);

    /**
     * Partially updates a userShipping.
     *
     * @param userShipping the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserShipping> partialUpdate(UserShipping userShipping);

    /**
     * Get all the userShippings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserShipping> findAll(Pageable pageable);

    /**
     * Get the "id" userShipping.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserShipping> findOne(Long id);

    /**
     * Delete the "id" userShipping.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
