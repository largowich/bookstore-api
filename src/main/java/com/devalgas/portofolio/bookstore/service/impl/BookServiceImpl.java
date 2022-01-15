package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.Book;
import com.devalgas.portofolio.bookstore.repository.BookRepository;
import com.devalgas.portofolio.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author devalgas kamga on 14/01/2022. 00:11
 * <p>
 * Service Implementation for managing {@link com.devalgas.portofolio.bookstore.domain.Book}.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        log.debug("Request to save Book : {}", book);
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> partialUpdate(Book book) {
        log.debug("Request to partially update Book : {}", book);

        return bookRepository
                .findById(book.getId())
                .map(
                        existingBook -> {
                            if (book.getTitle() != null) {
                                existingBook.setTitle(book.getTitle());
                            }
                            if (book.getAuthor() != null) {
                                existingBook.setAuthor(book.getAuthor());
                            }
                            if (book.getPublisher() != null) {
                                existingBook.setPublisher(book.getPublisher());
                            }
                            if (book.getPublicationDate() != null) {
                                existingBook.setPublicationDate(book.getPublicationDate());
                            }
                            if (book.getLanguage() != null) {
                                existingBook.setLanguage(book.getLanguage());
                            }
                            if (book.getCategory() != null) {
                                existingBook.setCategory(book.getCategory());
                            }
                            if (book.getNumberOfPages() != null) {
                                existingBook.setNumberOfPages(book.getNumberOfPages());
                            }
                            if (book.getFormat() != null) {
                                existingBook.setFormat(book.getFormat());
                            }
                            if (book.getIsbn() != null) {
                                existingBook.setIsbn(book.getIsbn());
                            }
                            if (book.getShippingWeight() != null) {
                                existingBook.setShippingWeight(book.getShippingWeight());
                            }
                            if (book.getListPrice() != null) {
                                existingBook.setListPrice(book.getListPrice());
                            }
                            if (book.getOurPrice() != null) {
                                existingBook.setOurPrice(book.getOurPrice());
                            }
                            if (book.getActive() != null) {
                                existingBook.setActive(book.getActive());
                            }
                            if (book.getDescription() != null) {
                                existingBook.setDescription(book.getDescription());
                            }
                            if (book.getInStockNumber() != null) {
                                existingBook.setInStockNumber(book.getInStockNumber());
                            }

                            return existingBook;
                        }
                )
                .map(bookRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable) {
        log.debug("Request to get all Books");
        return bookRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findOne(Long id) {
        log.debug("Request to get Book : {}", id);
        return bookRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Book : {}", id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        log.debug("Request to get all Books");
        return bookRepository.findAll().stream().filter(Book::getActive).collect(Collectors.toList());
    }

    @Override
    public List<Book> blurrySearch(String title) {
        log.debug("Request to get all Books by title : {}", title);
        return bookRepository.findByTitleContaining(title).stream().filter(Book::getActive).collect(Collectors.toList());
    }
}
