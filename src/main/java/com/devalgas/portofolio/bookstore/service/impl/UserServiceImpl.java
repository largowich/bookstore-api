package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.*;
import com.devalgas.portofolio.bookstore.domain.security.UserRole;
import com.devalgas.portofolio.bookstore.repository.*;
import com.devalgas.portofolio.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author devalgas kamga on 14/01/2022. 00:18
 * <p>
 * Service Implementation for managing {@link User}.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserBillingRepository userBillingRepository;

    private final UserPaymentRepository userPaymentRepository;

    private final UserShippingRepository userShippingRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserBillingRepository userBillingRepository, UserPaymentRepository userPaymentRepository, UserShippingRepository userShippingRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userBillingRepository = userBillingRepository;
        this.userPaymentRepository = userPaymentRepository;
        this.userShippingRepository = userShippingRepository;
    }

    @Override
    public User save(User user) {
        log.debug("Request to save User : {}", user);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> partialUpdate(User user) {
        log.debug("Request to partially update User : {}", user);

        return userRepository
                .findById(user.getId())
                .map(
                        existingUser -> {
                            return user;
                        }
                )
                .map(userRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        log.debug("Request to get all Users");
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete User : {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        log.debug("Request to create User : {}, {}", user, userRoles);

        User localUser = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (localUser != null) {
            log.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);

            user.setUserPaymentList(new ArrayList<UserPayment>());
            user.setUserShippingList(new ArrayList<UserShipping>());

            localUser = save(user);
        }

        return localUser;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.debug("Request to get User by username : {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.debug("Request to get User by email : {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user) {
        log.debug("Request to update  userbilling: {}, {}, {}", userBilling, userPayment, user);
        save(user);
        userBillingRepository.save(userBilling);
        userPaymentRepository.save(userPayment);
    }

    @Override
    public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
        log.debug("Request to update  userpayment: {}, {}, {}", userBilling, userPayment, user);
        userPayment.setUser(user);
        userPayment.setUserBilling(userBilling);
        userPayment.setDefaultPayment(true);
        userBilling.setUserPayment(userPayment);
        user.getUserPaymentList().add(userPayment);
        save(user);

    }

    @Override
    public void setUserDefaultPayment(Long userPaymentId, User user) {
        log.debug("Request to update user by  userpayment: {}, {}", userPaymentId, user);
        List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentRepository.findAll();

        for (UserPayment userPayment : userPaymentList) {
            if (Objects.equals(userPayment.getId(), userPaymentId)) {
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            } else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
            }
        }

    }

    @Override
    public void updateUserShipping(UserShipping userShipping, User user) {
        log.debug("Request to update user by usershipping: {}, {}", userShipping, user);
        userShipping.setUser(user);
        userShipping.setUserShippingDefault(true);
        user.getUserShippingList().add(userShipping);
        save(user);
    }

    @Override
    public void setUserDefaultShipping(Long userShippingId, User user) {
        log.debug("Request to update  usershipping: {}, {}", userShippingId, user);
        List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();

        for (UserShipping userShipping : userShippingList) {
            if (Objects.equals(userShipping.getId(), userShippingId)) {
                userShipping.setUserShippingDefault(true);
                userShippingRepository.save(userShipping);
            } else {
                userShipping.setUserShippingDefault(false);
                userShippingRepository.save(userShipping);
            }
        }
    }
}
