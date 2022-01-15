package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author devalgas kamga on 13/01/2022. 23:45
 * <p>
 * Spring Data SQL repository for the UserPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
}
