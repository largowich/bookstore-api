package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.*;
import com.devalgas.portofolio.bookstore.repository.BookToCartItemRepository;
import com.devalgas.portofolio.bookstore.repository.CartItemRepository;
import com.devalgas.portofolio.bookstore.service.CartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:13
 * <p>
 * Service Implementation for managing {@link com.devalgas.portofolio.bookstore.domain.CartItem}.
 */
@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);

    private final CartItemRepository cartItemRepository;

    private BookToCartItemRepository bookToCartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, BookToCartItemRepository bookToCartItemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.bookToCartItemRepository = bookToCartItemRepository;
    }

    @Override
    public CartItem save(CartItem cartItem) {
        log.debug("Request to save CartItem : {}", cartItem);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Optional<CartItem> partialUpdate(CartItem cartItem) {
        log.debug("Request to partially update CartItem : {}", cartItem);

        return cartItemRepository
                .findById(cartItem.getId())
                .map(
                        existingCartItem -> {
                            if (cartItem.getQty() != null) {
                                existingCartItem.setQty(cartItem.getQty());
                            }
                            if (cartItem.getSubtotal() != null) {
                                existingCartItem.setSubtotal(cartItem.getSubtotal());
                            }

                            return existingCartItem;
                        }
                )
                .map(cartItemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CartItem> findAll(Pageable pageable) {
        log.debug("Request to get all CartItems");
        return cartItemRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CartItem> findOne(Long id) {
        log.debug("Request to get CartItem : {}", id);
        return cartItemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CartItem : {}", id);
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem addBookToCartItem(Book book, User user, int qty) {
        log.debug("Request to addBookToCartItem CartItem : {}, {}, {}", book, user, qty);
        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
        for (CartItem cartItem : cartItemList) {
            if (Objects.equals(book.getId(), cartItem.getBook().getId())) {
                cartItem.setQty(cartItem.getQty() + qty);
                cartItem.setSubtotal(BigDecimal.valueOf(book.getOurPrice()).multiply(new BigDecimal(qty)));
                save(cartItem);
                return cartItem;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setBook(book);
        cartItem.setQty(qty);
        cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));

        cartItem = save(cartItem);

        BookToCartItem bookToCartItem = new BookToCartItem();
        bookToCartItem.setBook(book);
        bookToCartItem.setCartItem(cartItem);
        bookToCartItemRepository.save(bookToCartItem);
        return cartItem;
    }

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        log.debug("Request to get all CartItems by shoppingCart : {}", shoppingCart);
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public List<CartItem> findByOrder(Order order) {
        log.debug("Request to get all CartItems by order : {}", order);
        return cartItemRepository.findByOrder(order);
    }

    @Override
    public Optional<CartItem> updateCartItem(CartItem cartItem) {
        log.debug("Request to partially updateCartItem : {}", cartItem);
        cartItem.setSubtotal(BigDecimal.valueOf(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty())).setScale(2, RoundingMode.HALF_UP));
        return partialUpdate(cartItem);
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        log.debug("Request to remove CartItem : {}", cartItem);
        cartItemRepository.delete(cartItem);
    }
}
