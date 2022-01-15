package com.devalgas.portofolio.bookstore.resource;

import com.devalgas.portofolio.bookstore.service.UserService;
import com.devalgas.portofolio.bookstore.utility.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @author devalgas kamga on 15/01/2022. 14:00
 */
@RestController
@RequestMapping("/api")
public class LoginResource {
    private final Logger log = LoggerFactory.getLogger(CheckoutResource.class);

    private final UserService userService;

    public LoginResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@code GET  /login/token} : get the token.
     *
     * @param session the session of the HttpSession to retrieve.
     * @param request the request of the HttpServletRequest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shippingAddress, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/login/token")
    public ResponseEntity<Map<String, String>> token(HttpSession session, HttpServletRequest request) {
        log.debug("REST request to token : {}, {}", session, request.getRemoteHost());

        String remoteHost = request.getRemoteHost();
        int portNumber = request.getRemotePort();

        log.info("REST request to token, remoteHost {} : portNumber {}", remoteHost, remoteHost);
        log.info("REST request to token, remoteAddr {}", request.getRemoteAddr());

        return ResponseUtil.wrapOrNotFound(Optional.of(Collections.singletonMap("token", session.getId())));
    }


    /**
     * {@code GET  /login/checkSession} : check session.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the message: Session Active, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/login/checkSession")
    public ResponseEntity<String> checkSession() {
        log.debug("REST request to check session");
        return ResponseEntity.ok().body("Session Active!");
    }

    /**
     * {@code POST  /user/logout} : check logout.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the message: Logout Successfully!, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/user/logout")
    public ResponseEntity<String> logout() {
        log.debug("REST request to logout");
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body("Logout Successfully!");
    }
}
