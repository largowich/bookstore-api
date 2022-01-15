package com.devalgas.portofolio.bookstore.resource;

import com.devalgas.portofolio.bookstore.domain.User;
import com.devalgas.portofolio.bookstore.domain.UserShipping;
import com.devalgas.portofolio.bookstore.service.UserService;
import com.devalgas.portofolio.bookstore.service.UserShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * @author devalgas kamga on 15/01/2022. 15:07
 */
public class ShippingResource {
    private final Logger log = LoggerFactory.getLogger(PaymentResource.class);

    private final UserService userService;

    private final UserShippingService userShippingService;

    public ShippingResource(UserService userService, UserShippingService userShippingService) {
        this.userService = userService;
        this.userShippingService = userShippingService;
    }

    /**
     * {@code POST  /shipping/add} :add new user shipping post.
     *
     * @param userShipping the userShipping of userShipping to retrieve.
     * @param principal    the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: Shipping Added(Updated) Successfully!, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @PostMapping("/shipping/add")
    public ResponseEntity<String> addNewUserShippingPost(@RequestBody UserShipping userShipping, Principal principal) throws Exception {
        log.debug("REST request to add new user shipping post : {}", userShipping);
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));
        userService.updateUserShipping(userShipping, user);
        return ResponseEntity.ok().body("Shipping Added(Updated) Successfully!");
    }

    /**
     * {@code GET  getUserShippingList} :get user shipping list.
     *
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and the list of userShippings, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/getUserShippingList")
    public ResponseEntity<List<UserShipping>> getUserShippingList(Principal principal) throws Exception {
        log.debug("REST request to user shipping list");
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));
        List<UserShipping> userShippingList = user.getUserShippingList();
        return ResponseEntity.ok().body(userShippingList);
    }

    /**
     * {@code DEL  getUserShippingList} :remove user shipping post.
     *
     * @param id        the id of userShipping to retrieve.
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and the string: "Shipping Removed Successfully!".
     */
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeUserShippingPost(@RequestBody String id, Principal principal) {
        log.debug("REST request to remove user shipping post, {}", id);
        userShippingService.delete(Long.parseLong(id));
        return ResponseEntity.ok().body("Shipping Removed Successfully!");
    }

    /**
     * {@code GET  setDefault} :set default user shipping post.
     *
     * @param id        the id of user to retrieve.
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and the list of userPayments, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/setDefault")
    public ResponseEntity<String> setDefaultUserShippingPost(@RequestBody String id, Principal principal) throws Exception {
        log.debug("REST request to set default user shipping post, {}", id);
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));
        userService.setUserDefaultShipping(Long.parseLong(id), user);
        return ResponseEntity.ok().body("Set Default Shipping Successfully!");
    }
}
