package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author devalgas kamga on 13/01/2022. 23:41
 * <p>
 * Spring Data SQL repository for the Payment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
