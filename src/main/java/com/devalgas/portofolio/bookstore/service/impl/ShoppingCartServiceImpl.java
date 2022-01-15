package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.CartItem;
import com.devalgas.portofolio.bookstore.domain.ShoppingCart;
import com.devalgas.portofolio.bookstore.repository.ShoppingCartRepository;
import com.devalgas.portofolio.bookstore.service.CartItemService;
import com.devalgas.portofolio.bookstore.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:17
 * <p>
 * Service Implementation for managing {@link ShoppingCart}.
 */
@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    private final ShoppingCartRepository shoppingCartRepository;

    private final CartItemService cartItemService;


    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, CartItemService cartItemService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemService = cartItemService;
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        log.debug("Request to save ShoppingCart : {}", shoppingCart);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> partialUpdate(ShoppingCart shoppingCart) {
        log.debug("Request to partially update ShoppingCart : {}", shoppingCart);

        return shoppingCartRepository
                .findById(shoppingCart.getId())
                .map(
                        existingShoppingCart -> {
                            if (shoppingCart.getGrandTotal() != null) {
                                existingShoppingCart.setGrandTotal(shoppingCart.getGrandTotal());
                            }

                            return existingShoppingCart;
                        }
                )
                .map(shoppingCartRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ShoppingCart> findAll(Pageable pageable) {
        log.debug("Request to get all ShoppingCarts");
        return shoppingCartRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoppingCart> findOne(Long id) {
        log.debug("Request to get ShoppingCart : {}", id);
        return shoppingCartRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShoppingCart : {}", id);
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public Optional<ShoppingCart> updateShoppingCart(ShoppingCart shoppingCart) {
        log.debug("Request to partially updateShoppingCart : {}", shoppingCart);

        BigDecimal cartTotal = new BigDecimal(0);

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getBook().getInStockNumber() > 0) {
                cartItemService.updateCartItem(cartItem);
                cartTotal = cartTotal.add(cartItem.getSubtotal());
            }
        }

        shoppingCart.setGrandTotal(cartTotal);
        return partialUpdate(shoppingCart);
    }

    @Override
    public void clearShoppingCart(ShoppingCart shoppingCart) {
        log.debug("Request to delete ShoppingCart : {}", shoppingCart);

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            cartItem.setShoppingCart(null);
            cartItemService.save(cartItem);
        }

        shoppingCart.setGrandTotal(new BigDecimal(0));

        shoppingCartRepository.save(shoppingCart);
    }
}
