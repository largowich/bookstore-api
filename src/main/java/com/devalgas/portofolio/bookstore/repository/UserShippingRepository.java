package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.UserShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author devalgas kamga on 13/01/2022. 23:47
 * <p>
 * Spring Data SQL repository for the UserShipping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserShippingRepository extends JpaRepository<UserShipping, Long> {
}
