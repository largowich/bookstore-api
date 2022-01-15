package com.devalgas.portofolio.bookstore.resource;

import com.devalgas.portofolio.bookstore.domain.User;
import com.devalgas.portofolio.bookstore.domain.UserBilling;
import com.devalgas.portofolio.bookstore.domain.UserPayment;
import com.devalgas.portofolio.bookstore.service.UserPaymentService;
import com.devalgas.portofolio.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

/**
 * @author devalgas kamga on 15/01/2022. 14:48
 */
@RequestMapping("/api")
public class PaymentResource {

    private final Logger log = LoggerFactory.getLogger(PaymentResource.class);

    private UserService userService;

    private UserPaymentService userPaymentService;

    /**
     * {@code POST  /payment/add} :add new credit card post.
     *
     * @param userPayment the userPayment of the UserPayment to retrieve.
     * @param principal   the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: Payment Added(Updated) Successfully!, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException        if the signals that an I/O exception to some sort has occurred.
     */
    @PostMapping("/payment/add")
    public ResponseEntity<String> addNewCreditCardPost(@RequestBody UserPayment userPayment, Principal principal) throws Exception {
        log.debug("REST request to add new credit card post : {} ", userPayment);

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));

        UserBilling userBilling = userPayment.getUserBilling();

        userService.updateUserBilling(userBilling, userPayment, user);

        return ResponseEntity.ok().body("Payment Added(Updated) Successfully!");
    }


    /**
     * {@code POST  /payment/remove} :remove payment post.
     *
     * @param id        the id of payment to retrieve.
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: Payment Removed Successfully!, or with status {@code 500 (Internal Server Error)} if the user nul.
     */
    @PostMapping("/payment/remove")
    public ResponseEntity<String> removePaymentPost(@RequestBody String id, Principal principal) {
        log.debug("REST request to remove payment post : {} ", id);

//		User user = userService.findByUsername(principal.getName());

        userPaymentService.delete(Long.valueOf(id));

        return ResponseEntity.ok().body("Payment Removed Successfully!");
    }

    /**
     * {@code GET  /payment/setDefault} :set default payment post.
     *
     * @param id        the id of userPaymentId the retrieve.
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: Payment Removed Successfully!, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/payment/setDefault")
    public ResponseEntity<String> setDefaultPaymentPost(@RequestBody String id, Principal principal) throws Exception {
        log.debug("REST request to set default payment post : {} ", id);

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));

        userService.setUserDefaultPayment(Long.parseLong(id), user);

        return ResponseEntity.ok().body("Payment Removed Successfully!");
    }


    /**
     * {@code GET  /payment/getUserPaymentList} :set default payment post.
     *
     * @param principal the principal of Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and the list of userPayments, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/payment/getUserPaymentList")
    public ResponseEntity<List<UserPayment>> getUserPaymentList(Principal principal) throws Exception {
        log.debug("REST request to get user payment list");

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));

        List<UserPayment> userPaymentList = user.getUserPaymentList();

        return ResponseEntity.ok().body(userPaymentList);
    }
}
