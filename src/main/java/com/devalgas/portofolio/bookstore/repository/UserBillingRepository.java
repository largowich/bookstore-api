package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.UserBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author devalgas kamga on 13/01/2022. 23:43
 * <p>
 * Spring Data SQL repository for the UserBilling entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserBillingRepository extends JpaRepository<UserBilling, Long> {
}
