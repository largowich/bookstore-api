package com.devalgas.portofolio.bookstore.resource;

import com.devalgas.portofolio.bookstore.domain.*;
import com.devalgas.portofolio.bookstore.service.CartItemService;
import com.devalgas.portofolio.bookstore.service.OrderService;
import com.devalgas.portofolio.bookstore.service.ShoppingCartService;
import com.devalgas.portofolio.bookstore.service.UserService;
import com.devalgas.portofolio.bookstore.utility.MailConstructor;
import com.devalgas.portofolio.bookstore.utility.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 02:36
 */
@RequestMapping("/api")
public class CheckoutResource {
    private final Logger log = LoggerFactory.getLogger(CheckoutResource.class);

    private static final String ENTITY_NAME = "order";

    @Value("${bookstore.clientApp.name}")
    private String applicationName;

    private final JavaMailSender mailSender;

    private final UserService userService;

    private final CartItemService cartItemService;

    private final OrderService orderService;

    private final ShoppingCartService shoppingCartService;

    private final MailConstructor mailConstructor;

    public CheckoutResource(JavaMailSender mailSender, UserService userService, CartItemService cartItemService, OrderService orderService, ShoppingCartService shoppingCartService, MailConstructor mailConstructor) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.mailConstructor = mailConstructor;
    }

    /**
     * {@code Get  /checkout} : checkout post.
     *
     * @param mapper the mapper key shippingAddress ,
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the order is not valid,
     * or with status {@code 500 (Internal Server Error)} if the order couldn't be checking.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException        if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/checkout")
    public ResponseEntity<Order> checkoutPost(@RequestBody HashMap<String, Object> mapper, Principal principal) throws Exception {
        log.debug("REST request to checkout post : {}, {}", mapper, principal);

        ObjectMapper om = new ObjectMapper();

        ShippingAddress shippingAddress = om.convertValue(mapper.get("shippingAddress"), ShippingAddress.class);
        BillingAddress billingAddress = om.convertValue(mapper.get("billingAddress"), BillingAddress.class);
        Payment payment = om.convertValue(mapper.get("payment"), Payment.class);
        String shippingMethod = (String) mapper.get("shippingMethod");

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("User not found"));

        ShoppingCart shoppingCart = user.getShoppingCart();
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);

        mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));

        shoppingCartService.clearShoppingCart(shoppingCart);

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(order));
    }
}
