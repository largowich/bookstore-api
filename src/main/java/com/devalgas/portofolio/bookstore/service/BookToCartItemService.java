package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.BookToCartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:03
 * <p>
 * Service Interface for managing {@link BookToCartItem}.
 */
public interface BookToCartItemService {
    /**
     * Save a bookToCartItem.
     *
     * @param bookToCartItem the entity to save.
     * @return the persisted entity.
     */
    BookToCartItem save(BookToCartItem bookToCartItem);

    /**
     * Partially updates a bookToCartItem.
     *
     * @param bookToCartItem the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BookToCartItem> partialUpdate(BookToCartItem bookToCartItem);

    /**
     * Get all the bookToCartItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BookToCartItem> findAll(Pageable pageable);

    /**
     * Get the "id" bookToCartItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BookToCartItem> findOne(Long id);

    /**
     * Delete the "id" bookToCartItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
