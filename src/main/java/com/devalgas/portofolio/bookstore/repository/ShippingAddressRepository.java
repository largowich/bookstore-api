package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author devalgas kamga on 13/01/2022. 23:42
 * <p>
 * Spring Data SQL repository for the ShippingAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}
