package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.User;
import com.devalgas.portofolio.bookstore.domain.UserBilling;
import com.devalgas.portofolio.bookstore.domain.UserPayment;
import com.devalgas.portofolio.bookstore.domain.UserShipping;
import com.devalgas.portofolio.bookstore.domain.security.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

/**
 * @author devalgas kamga on 14/01/2022. 00:07
 * <p>
 * Service Interface for managing {@link User}.
 */
public interface UserService {
    /**
     * Save a user.
     *
     * @param user the entity to save.
     * @return the persisted entity.
     */
    User save(User user);

    /**
     * Partially updates a user.
     *
     * @param user the entity to update partially.
     * @return the persisted entity.
     */
    Optional<User> partialUpdate(User user);

    /**
     * Get all the users.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<User> findAll(Pageable pageable);

    /**
     * Get the "id" user.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<User> findOne(Long id);

    /**
     * Delete the "id" user.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * create a user.
     *
     * @param user      the user of the entity.
     * @param userRoles the userRoles of the entity.
     * @return the persisted entity.
     */
    User createUser(User user, Set<UserRole> userRoles);

    /**
     * Get the "username" user.
     *
     * @param username the username of the entity.
     * @return the entity.
     */
    Optional<User> findByUsername(String username);

    /**
     * Get the "email" user.
     *
     * @param email the email of the entity.
     * @return the entity.
     */
    Optional<User> findByEmail(String email);

    /**
     * Partially updates a userBilling, userPayment and user.
     *
     * @param userBilling the userBilling is an entity.
     * @param userPayment the userPayment is an entity.
     * @param user        the user of the entity.
     */
    void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user);

    /**
     * Partially updates a user.
     *
     * @param userBilling the userBilling is an entity.
     * @param userPayment the userPayment is an entity.
     * @param user        the user of the entity.
     */
    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

    /**
     * Partially updates a userPayment.
     *
     * @param userPaymentId the id of the userPayment.
     * @param user          the user  of the entity.
     */
    void setUserDefaultPayment(Long userPaymentId, User user);

    /**
     * Partially updates a user.
     *
     * @param userShipping the userShipping is an entity.
     * @param user         the user of the entity.
     */
    void updateUserShipping(UserShipping userShipping, User user);

    /**
     * Partially updates a userPayment.
     *
     * @param userShippingId the id of the userShipping.
     * @param user           the user  of the entity.
     */
    void setUserDefaultShipping(Long userShippingId, User user);
}
