package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author devalgas kamga on 13/01/2022. 23:37
 * Spring Data SQL repository for the BillingAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
