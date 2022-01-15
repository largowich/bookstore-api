package com.devalgas.portofolio.bookstore.resource;

import com.devalgas.portofolio.bookstore.domain.Order;
import com.devalgas.portofolio.bookstore.domain.User;
import com.devalgas.portofolio.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

/**
 * REST controller for managing {@link Order}.
 */
@RestController
@RequestMapping("/api")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private final UserService userService;

    public OrderResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@code Get  /checkout} : Get Order List.
     *
     * @param principal the principal of Principal ,
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of order in body,
     * or with status {@code 400 (Bad Request)} if the order is not valid,
     * or with status {@code 500 (Internal Server Error)} if the order couldn't be getting.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException        if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/order/getOrderList")
    public ResponseEntity<List<Order>> getOrderList(Principal principal) throws Exception {
        log.debug("REST request to get order");

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));
        try {
            List<Order> orderList = user.getOrderList();
            return ResponseEntity.ok().body(orderList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
