package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.BookToCartItem;
import com.devalgas.portofolio.bookstore.repository.BookToCartItemRepository;
import com.devalgas.portofolio.bookstore.service.BookToCartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:13
 * Service Implementation for managing {@link BookToCartItem}.
 */
@Service
@Transactional
public class BookToCartItemServiceImpl implements BookToCartItemService {

    private final Logger log = LoggerFactory.getLogger(BookToCartItemServiceImpl.class);

    private final BookToCartItemRepository bookToCartItemRepository;

    public BookToCartItemServiceImpl(BookToCartItemRepository bookToCartItemRepository) {
        this.bookToCartItemRepository = bookToCartItemRepository;
    }

    @Override
    public BookToCartItem save(BookToCartItem bookToCartItem) {
        log.debug("Request to save BookToCartItem : {}", bookToCartItem);
        return bookToCartItemRepository.save(bookToCartItem);
    }

    @Override
    public Optional<BookToCartItem> partialUpdate(BookToCartItem bookToCartItem) {
        log.debug("Request to partially update BookToCartItem : {}", bookToCartItem);

        return bookToCartItemRepository
                .findById(bookToCartItem.getId())
                .map(
                        existingBookToCartItem -> {
                            return existingBookToCartItem;
                        }
                )
                .map(bookToCartItemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookToCartItem> findAll(Pageable pageable) {
        log.debug("Request to get all BookToCartItems");
        return bookToCartItemRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookToCartItem> findOne(Long id) {
        log.debug("Request to get BookToCartItem : {}", id);
        return bookToCartItemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookToCartItem : {}", id);
        bookToCartItemRepository.deleteById(id);
    }
}
