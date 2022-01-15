package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.*;
import com.devalgas.portofolio.bookstore.repository.BillingAddressRepository;
import com.devalgas.portofolio.bookstore.repository.OrderRepository;
import com.devalgas.portofolio.bookstore.repository.PaymentRepository;
import com.devalgas.portofolio.bookstore.repository.ShippingAddressRepository;
import com.devalgas.portofolio.bookstore.service.BookService;
import com.devalgas.portofolio.bookstore.service.CartItemService;
import com.devalgas.portofolio.bookstore.service.OrderService;
import com.devalgas.portofolio.bookstore.utility.MailConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author devalgas kamga on 14/01/2022. 00:14
 * <p>
 * Service Implementation for managing {@link Order}.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final BillingAddressRepository billingAddressRepository;

    private final ShippingAddressRepository shippingAddressRepository;

    private final PaymentRepository paymentRepository;

    private final CartItemService cartItemService;

    private final BookService bookService;

    private final MailConstructor mailConstructor;

    public OrderServiceImpl(OrderRepository orderRepository, BillingAddressRepository billingAddressRepository, ShippingAddressRepository shippingAddressRepository, PaymentRepository paymentRepository, CartItemService cartItemService, BookService bookService, MailConstructor mailConstructor) {
        this.orderRepository = orderRepository;
        this.billingAddressRepository = billingAddressRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.paymentRepository = paymentRepository;
        this.cartItemService = cartItemService;
        this.bookService = bookService;
        this.mailConstructor = mailConstructor;
    }

    @Override
    public Order save(Order order) {
        log.debug("Request to save Order : {}", order);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> partialUpdate(Order order) {
        log.debug("Request to partially update Order : {}", order);

        return orderRepository
                .findById(order.getId())
                .map(
                        existingOrder -> {
                            if (order.getOrderDate() != null) {
                                existingOrder.setOrderDate(order.getOrderDate());
                            }
                            if (order.getShippingDate() != null) {
                                existingOrder.setShippingDate(order.getShippingDate());
                            }
                            if (order.getShippingMethod() != null) {
                                existingOrder.setShippingMethod(order.getShippingMethod());
                            }
                            if (order.getOrderStatus() != null) {
                                existingOrder.setOrderStatus(order.getOrderStatus());
                            }
                            if (order.getOrderTotal() != null) {
                                existingOrder.setOrderTotal(order.getOrderTotal());
                            }

                            return existingOrder;
                        }
                )
                .map(orderRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Order> findAll(Pageable pageable) {
        log.debug("Request to get all Orders");
        return orderRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }

    @Override
    public Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, String shippingMethod, User user) {
        log.debug("Request to create Order : {}, {},{}, {}, {}, {}", shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);

        Order order = new Order();
        order.setBillingAddress(billingAddress);
        order.setOrderStatus("created");
        order.setPayment(payment);
        order.setShippingAddress(shippingAddress);
        order.setShippingMethod(shippingMethod);

        Set<CartItem> cartItemList = new HashSet<>(cartItemService.findByShoppingCart(shoppingCart));

        for (CartItem cartItem : cartItemList) {
            Book book = cartItem.getBook();
            cartItem.setOrder(order);
            book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
        }

        order.setCartItems(cartItemList);
        order.setEstimatedDeliveryDate(shippingMethod.equals("groundShipping") ? LocalDate.now().plusDays(5) : LocalDate.now().plusDays(3));
        order.setOrderDate(Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        order.setOrderTotal(shoppingCart.getGrandTotal());
        shippingAddress.setOrder(order);
        billingAddress.setOrder(order);
        payment.setOrder(order);
        order.setUser(user);

        return orderRepository.save(order);
    }
}
